package com.example.studyroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomDTO {
    private int roomId;
    private int remainSeats;
    private int seats;
    private int usedSeats;
}
