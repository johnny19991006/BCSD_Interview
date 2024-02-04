package com.example.studyroom.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
public class Room {

    public Room() {
    }

    @Id
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "used_seats")
    private Integer usedSeats;

    @Column(name = "remain_seats")
    private Integer remainSeats;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<Seat> seatList = new ArrayList<>();

    @Builder
    public Room(Integer roomId, Integer seats, Integer usedSeats, Integer remainSeats, List<Seat> seatList) {
        this.roomId = roomId;
        this.seats = seats;
        this.usedSeats = usedSeats;
        this.remainSeats = remainSeats;
        this.seatList = seatList;
    }

    public void usedSeats(Integer usedSeats) {
        this.usedSeats = usedSeats;
        this.remainSeats = seats - usedSeats;
    }
}
