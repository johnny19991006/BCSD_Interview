package com.example.studyroom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "rooms")
@Getter
@Setter
public class Room {
    @Id
    @Column
    private int room_id;

    @Column
    private int seats;

    @Column(name = "used_seats")
    private int usedSeats;

    @Column(name = "remain_seats")
    private int remainSeats;

    public Room() {
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<Seat> seatList = new ArrayList<>();
}
