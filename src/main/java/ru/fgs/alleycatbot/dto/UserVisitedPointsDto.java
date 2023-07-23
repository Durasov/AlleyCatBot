package ru.fgs.alleycatbot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVisitedPointsDto {

    private String userName;
    private Long pointId;
    private String pointName;
    private LocalDateTime visitedTime;

}
