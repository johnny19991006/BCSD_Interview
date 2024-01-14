package com.example.studyroom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "seats")
@AllArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    @Column(name = "seat_id")
    private int seatId;

    @Column(name = "is_used")
    private boolean isUsed;

    @Column(name = "user_id")
    private int userId;

    @Column
    private int room_id;

    @Column(name = "start_time")
    @CreationTimestamp
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "remain_time")
    private LocalDateTime remainTime;

    public Seat() {
    }

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "school_id")
    private User user;
}
