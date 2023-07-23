package ru.fgs.alleycatbot.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Component
@RequiredArgsConstructor
public class SendStickerHelper {

    public SendSticker getWelcomeSticker(long chatId) {
        return SendSticker.builder()
                .chatId(String.valueOf(chatId))
                .sticker(new InputFile("CAACAgIAAxkBAAPpZI4E1zZuu9ZEh2CZKaW7nSPJBJ4AAt8SAALNEslLFNOT4qeo72cvBA"))
                .build();
    }

}
