package com.example.studyroom.repository;

import com.example.studyroom.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByRoomId(Integer roomId);
}
