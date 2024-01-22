package com.example.studyroom.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "rooms")
@Getter
@Setter
public class Room {
    @Id
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "used_seats")
    private Integer usedSeats;

    @Column(name = "remain_seats")
    private Integer remainSeats;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private List<Seat> seatList = new ArrayList<>();
}
