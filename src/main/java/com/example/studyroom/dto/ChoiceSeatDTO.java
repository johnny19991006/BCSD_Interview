package com.example.studyroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ChoiceSeatDTO {
    private Integer seatId;
    private Integer seatNum;
    private Integer schoolId;
    private Integer roomId;
    private Boolean isUsed;
}
