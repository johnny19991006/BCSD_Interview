package com.example.studyroom.service;

import com.example.studyroom.domain.Room;
import com.example.studyroom.dto.RoomDTO;
import com.example.studyroom.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room insertRoom(RoomDTO room) {
        return roomRepository.save(Room.builder()
                .roomId(room.getRoomId())
                .seats(room.getSeats())
                .build());
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByRoomId(Integer roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public void deleteRoom(Integer roomId) {
        roomRepository.delete(Room.builder().roomId(roomId).build());
    }
}
