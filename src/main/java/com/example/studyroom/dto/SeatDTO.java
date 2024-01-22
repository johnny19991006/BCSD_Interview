package com.example.studyroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SeatDTO {
    private Integer seatId;
    private Integer roomId;
    private Integer seatNum;
    private Integer schoolId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isUsed;
}
