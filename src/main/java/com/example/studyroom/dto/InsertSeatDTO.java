package com.example.studyroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class InsertSeatDTO {
    private Integer seatNum;
    private Integer roomId;
}
