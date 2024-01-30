package com.example.studyroom.domain;

import com.example.studyroom.Message;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "seats")
@Getter
public class Seat {
    public Seat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "seat_number")
    private Integer seatNum;

    @Builder.Default
    @Column(name = "is_used")
    private Boolean isUsed = false;

    @Column(name = "start_time")
    @CreationTimestamp
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;

    @OneToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private User user;

    public Seat(Integer seatNum, Room room) {
        this.seatNum = seatNum;
        this.room = room;
    }

    public Seat(Integer seatId) {
        this.seatId = seatId;
    }

    public Seat(Integer seatId, Integer seatNum, Boolean isUsed, LocalDateTime startTime, LocalDateTime endTime,
                Room room, User user) {
        this.seatId = seatId;
        this.seatNum = seatNum;
        this.isUsed = isUsed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.user = user;
    }

    public void endSeatUpdate() {
        if (!isUsed) {
            throw new IllegalArgumentException(Message.NON_EXISTENT_USER.getMessage());
        }

        this.user = null;
        this.startTime = null;
        this.endTime = null;
        this.isUsed = false;
    }

    public void choiceSeatUpdate(Integer seatNum, User user, Room room) {

        LocalDateTime now = LocalDateTime.now();

        this.seatNum = seatNum;
        this.isUsed = true;
        this.user = user;
        this.room = room;
        this.startTime = now;
        this.endTime = now.plusHours(3);
    }

    public void extendSeat(){
        this.endTime =  this.endTime.plusHours(3);
    }
}
