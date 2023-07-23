package ru.fgs.alleycatbot.helper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.fgs.alleycatbot.dto.UserVisitedPointsDto;
import ru.fgs.alleycatbot.entity.PointQuestionsEntity;
import ru.fgs.alleycatbot.entity.UserEntity;
import ru.fgs.alleycatbot.entity.UserVisitedPointsEntity;
import ru.fgs.alleycatbot.repository.PointQuestionsRepository;
import ru.fgs.alleycatbot.repository.UserRepository;
import ru.fgs.alleycatbot.repository.UserVisitedPointsRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.fgs.alleycatbot.constants.BotConstants.*;

@Component
@RequiredArgsConstructor
public class SendMessageHelper {

    private final UserRepository userRepository;
    private final UserVisitedPointsRepository userVisitedPointsRepository;
    private final PointQuestionsRepository pointQuestionsRepository;

    public ImmutablePair<SendMessage, Boolean> startCommand(long chatId, String userName) {
        UserEntity user = userRepository.findById(chatId)
                .orElse(null);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        if (user != null) {
            message.setText(ALREADY_SAY_HELLO_LIST.get(RANDOM.nextInt(ALREADY_SAY_HELLO_LIST.size())));
            return new ImmutablePair<>(message, Boolean.FALSE);
        }
        UserEntity savedUser = userRepository.save(new UserEntity(chatId, userName, 0));
        message.setText(getFirstPointMessage(savedUser.getUserName()));
        return new ImmutablePair<>(message, Boolean.TRUE);
    }

    public SendMessage onThePointCommand(long chatId) {
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(ENTER_CODE)
                .build();
    }

    public SendMessage helpCommand(long chatId) {
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(HELP_MESSAGE)
                .build();
    }

    public SendMessage topCommand(long chatId) {
        String message = BEST_OF_THE_BESTS
                + System.lineSeparator()
                + userRepository.findAll().stream()
                .sorted(Comparator.comparing(UserEntity::getUserPoints).reversed())
                .map(x -> x.getUserName() + " - " + x.getUserPoints())
                .collect(Collectors.joining(System.lineSeparator()));
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(message)
                .build();
    }

    @Transactional
    public SendMessage deleteCommand(long chatId) {
        userRepository.deleteById(chatId);
        userVisitedPointsRepository.deleteByUserId(chatId);
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Данные удалены")
                .build();
    }

    public SendMessage visitedPointsCommand(long chatId) {
        List<UserVisitedPointsDto> userVisitedPointsList = userVisitedPointsRepository.findAllUsersVisitedPoints();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String message = userVisitedPointsList.stream()
                .sorted(Comparator.comparing(UserVisitedPointsDto::getUserName)
                        .thenComparing(UserVisitedPointsDto::getPointId)
                        .thenComparing(UserVisitedPointsDto::getVisitedTime))
                .map(x -> x.getUserName()
                        + " " + x.getPointId()
                        + ". " + x.getPointName()
                        + " - " + x.getVisitedTime().format(formatter))
                .collect(Collectors.joining(System.lineSeparator()));
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(message)
                .build();
    }

    public SendMessage unexpectedCommand(long chatId) {
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(UNEXPECTED_COMMAND_LIST.get(RANDOM.nextInt(UNEXPECTED_COMMAND_LIST.size())))
                .build();
    }

    public SendMessage sendPointQuestion(long chatId, String code) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        UserVisitedPointsEntity userVisitedPointsEntity =
                userVisitedPointsRepository.findByUserIdAndUserVisitedPointCode(chatId, code)
                .orElse(null);
        if (userVisitedPointsEntity != null) {
            message.setText(ALREADY_WAS_HERE_LIST.get(RANDOM.nextInt(ALREADY_WAS_HERE_LIST.size())));
            return message;
        }
        PointQuestionsEntity pointQuestionsEntity = pointQuestionsRepository.findByPointCode(code)
                .orElse(PointQuestionsEntity.builder()
                        .pointQuestion(INCORRECT_CODE)
                        .build());
        String question = pointQuestionsEntity.getPointQuestion();
        message.setText(question);
        if (!INCORRECT_CODE.equals(question)) {
            message.setReplyMarkup(getInlineKeyboardMarkup(code));
            userVisitedPointsRepository.save(new UserVisitedPointsEntity(chatId, code));
        }
        return message;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup(String code) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(A);
        inlineKeyboardButton1.setCallbackData(A + UNDERSCORE + code);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(B);
        inlineKeyboardButton2.setCallbackData(B + UNDERSCORE + code);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(C);
        inlineKeyboardButton3.setCallbackData(C + UNDERSCORE + code);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText(D);
        inlineKeyboardButton4.setCallbackData(D + UNDERSCORE + code);

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(inlineKeyboardButton3);
        keyboardButtonsRow2.add(inlineKeyboardButton4);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private String getFirstPointMessage(String userName) {
        PointQuestionsEntity pointQuestionsEntity = pointQuestionsRepository.findById(1L)
                .orElseThrow();
        return  "Привет, " + userName
                + "! Итак, твоя первая точка " + pointQuestionsEntity.getPointName()
                + ". Как доедешь ищи там код, вводи команду /onthespot, присылай код, а дальше разберёмся!";
    }

}
