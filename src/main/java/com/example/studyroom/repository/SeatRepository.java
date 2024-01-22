package com.example.studyroom.repository;

import com.example.studyroom.domain.Seat;
import com.example.studyroom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Boolean existsByUser(User user);
}
