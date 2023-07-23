package ru.fgs.alleycatbot.telegram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.fgs.alleycatbot.entity.PointQuestionsEntity;
import ru.fgs.alleycatbot.entity.UserEntity;
import ru.fgs.alleycatbot.repository.PointQuestionsRepository;
import ru.fgs.alleycatbot.repository.UserRepository;

import static ru.fgs.alleycatbot.constants.BotConstants.*;

@Component
@RequiredArgsConstructor
public class CallbackHandler {

    private final UserRepository userRepository;
    private final PointQuestionsRepository pointQuestionsRepository;

    public SendMessage processCallback(CallbackQuery callbackQuery) {
        Message callbackMessage = callbackQuery.getMessage();
        long chatId = callbackMessage.getChatId();
        String userName = callbackMessage.getChat().getUserName();
        String data = callbackQuery.getData();
        String[] arrayOfIdAndAnswer = data.split(UNDERSCORE);
        String userAnswer = arrayOfIdAndAnswer[0];
        String answerId = arrayOfIdAndAnswer[1];

        PointQuestionsEntity pointQuestionsEntity = pointQuestionsRepository.findByPointCode(answerId)
                .orElseThrow();
        String nextPointCode = pointQuestionsEntity.getNextPoint();
        String correctAnswer = pointQuestionsEntity.getCorrectAnswer();
        String result;
        if (StringUtils.hasText(nextPointCode)) {
            PointQuestionsEntity nextPoint = pointQuestionsRepository.findByPointCode(nextPointCode)
                    .orElseThrow();
            String nextPointAddress = NEXT_POINT + nextPoint.getPointName();
            result = getResult(userAnswer, correctAnswer, userName, chatId, nextPointAddress);
            return SendMessage.builder()
                    .chatId(String.valueOf(chatId))
                    .text(result)
                    .build();
        }
        result = getResult(userAnswer, correctAnswer, userName, chatId, "");
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(result)
                .build();
    }

    private String getResult(String userAnswer,
                             String correctAnswer,
                             String userName,
                             long chatId,
                             String nextPointAddress) {
        if (userAnswer.equals(correctAnswer)) {
            UserEntity userEntity = userRepository.findById(chatId)
                    .orElseGet(() -> userRepository.save(new UserEntity(chatId, userName, 0)));
            Integer userPoints = userEntity.getUserPoints();
            userEntity.setUserPoints(++userPoints);
            userRepository.save(userEntity);
            return CORRECT_ANSWER + nextPointAddress;
        }
        return INCORRECT_ANSWER + nextPointAddress;
    }

}
