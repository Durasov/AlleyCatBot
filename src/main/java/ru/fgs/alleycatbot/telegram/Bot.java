package ru.fgs.alleycatbot.telegram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.fgs.alleycatbot.helper.SendMessageHelper;
import ru.fgs.alleycatbot.helper.SendLocationHelper;
import ru.fgs.alleycatbot.helper.SendStickerHelper;

import java.util.List;

import static ru.fgs.alleycatbot.constants.BotConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    @Getter
    private String botUsername;
    @Value("${bot.token}")
    @Getter
    private String botToken;
    private Boolean onTheSpot = Boolean.FALSE;

    private final CallbackHandler callbackHandler;
    private final SendLocationHelper sendLocationHelper;
    private final SendMessageHelper sendMessageHelper;
    private final SendStickerHelper sendStickerHelper;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message message = update.getMessage();
                String messageText = message.getText();
                long chatId = message.getChatId();
                String userName = message.getFrom().getUserName();

                if (onTheSpot) {
                    execute(sendMessageHelper.sendPointQuestion(chatId, messageText));
                    onTheSpot = Boolean.FALSE;
                    return;
                }
                switch (messageText) {
                    case START -> {
                        ImmutablePair<SendMessage, Boolean> startPair = sendMessageHelper.startCommand(chatId, userName);
                        if (startPair.right) {
                            execute(sendStickerHelper.getWelcomeSticker(chatId));
                            execute(startPair.left);
                            Thread.sleep(1000);
                            execute(sendLocationHelper.getCoordinatesOfFirstPoint(chatId));
                        } else {
                            execute(startPair.left);
                        }
                    }
                    case ON_THE_POINT -> {
                        execute(sendMessageHelper.onThePointCommand(chatId));
                        onTheSpot = Boolean.TRUE;
                    }
                    case HELP -> execute(sendMessageHelper.helpCommand(chatId));
                    case TOP -> execute(sendMessageHelper.topCommand(chatId));
                    case DELETE -> execute(sendMessageHelper.deleteCommand(chatId));
                    case VISITED_POINTS -> execute(sendMessageHelper.visitedPointsCommand(chatId));
                    default -> execute(sendMessageHelper.unexpectedCommand(chatId));
                }
            }
            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                Message callbackMessage = callbackQuery.getMessage();
                long chatId = callbackMessage.getChatId();
                String data = callbackQuery.getData();
                execute(callbackHandler.processCallback(callbackQuery));
                execute(sendLocationHelper.getCoordinates(chatId, data));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

}
