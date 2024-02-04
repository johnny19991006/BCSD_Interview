package com.example.studyroom.controller;

import com.example.studyroom.domain.Room;
import com.example.studyroom.dto.RoomDTO;
import com.example.studyroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("")
    public Room insertRoom(@RequestBody RoomDTO room) {
        return roomService.insertRoom(room);
    }

    @GetMapping("")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomId}")
    public Room getRoomByRoomID(@PathVariable int roomId) {
        return roomService.getRoomByRoomId(roomId);
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable int roomId) {
        roomService.deleteRoom(roomId);
    }

}
