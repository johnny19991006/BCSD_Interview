package com.example.studyroom.repository;

import com.example.studyroom.domain.Seat;
import com.example.studyroom.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Boolean existsByUser(User user);

    @Query("SELECT s.seatId FROM Seat s WHERE s.user.schoolId = :schoolId")
    Integer findSeatIdByUserSchoolId(@Param("schoolId") Integer schoolId);

    @Query("SELECT s.room.roomId FROM Seat s WHERE s.seatId = :seatId")
    Integer findRoomIdBySeatId(@Param("seatId") Integer seatId);

    @Query("SELECT s.isUsed FROM Seat s WHERE s.seatId = :seatId")
    Boolean findIsUsedBySeatId(@Param("seatId") Integer seatId);

    @Query("SELECT s.seatNum FROM Seat s WHERE s.seatId = :seatId")
    Integer findSeatNumBySeatId(@Param("seatId") Integer seatId);
}
