package ru.fgs.alleycatbot.helper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import ru.fgs.alleycatbot.entity.PointQuestionsEntity;
import ru.fgs.alleycatbot.repository.PointQuestionsRepository;

import static ru.fgs.alleycatbot.constants.BotConstants.UNDERSCORE;

@Component
@RequiredArgsConstructor
public class SendLocationHelper {

    private final PointQuestionsRepository pointQuestionsRepository;

    public SendLocation getCoordinatesOfFirstPoint(long chatId) {
        PointQuestionsEntity pointQuestionsEntity = pointQuestionsRepository.findById(1L)
                .orElseThrow();
        ImmutablePair<Double, Double> pairOfLatitudeAndLongitude = getLatitudeAndLongitude(pointQuestionsEntity.getPointCoordinates());
        return SendLocation.builder()
                .chatId(chatId)
                .latitude(pairOfLatitudeAndLongitude.left)
                .longitude(pairOfLatitudeAndLongitude.right)
                .build();
    }

    public SendLocation getCoordinates(long chatId, String data) {
        String[] arrayOfIdAndAnswer = data.split(UNDERSCORE);
        String answerId = arrayOfIdAndAnswer[1];
        PointQuestionsEntity pointQuestionsEntity = pointQuestionsRepository.findByPointCode(answerId)
                .orElseThrow();
        PointQuestionsEntity nextPoint = pointQuestionsRepository.findByPointCode(pointQuestionsEntity.getNextPoint())
                .orElseThrow();
        ImmutablePair<Double, Double> pairOfLatitudeAndLongitude = getLatitudeAndLongitude(nextPoint.getPointCoordinates());
        return SendLocation.builder()
                .chatId(chatId)
                .latitude(pairOfLatitudeAndLongitude.left)
                .longitude(pairOfLatitudeAndLongitude.right)
                .build();
    }

    private ImmutablePair<Double, Double> getLatitudeAndLongitude(String coordinates) {
        String[] arrayOfCoordinates = coordinates.split(", ");
        Double latitude = Double.valueOf(arrayOfCoordinates[0]);
        Double longitude = Double.valueOf(arrayOfCoordinates[1]);
        return new ImmutablePair<>(latitude, longitude);
    }

}
