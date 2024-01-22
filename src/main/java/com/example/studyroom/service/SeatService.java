package com.example.studyroom.service;

import com.example.studyroom.domain.Room;
import com.example.studyroom.domain.Seat;
import com.example.studyroom.dto.InsertSeatDTO;
import com.example.studyroom.repository.SeatRepository;
import com.example.studyroom.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    public SeatService(SeatRepository seatRepository, UserRepository userRepository) {
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }

    public Seat insertSeat(InsertSeatDTO insertSeatDTO) {

        return seatRepository.save(Seat.builder()
                .seatNum(insertSeatDTO.getSeatNum())
                .room(Room.builder().roomId(insertSeatDTO.getRoomId()).build())
                .build());
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat getSeatBySeatID(Integer seatId) {
        return seatRepository.findById(seatId).orElse(null);
    }

    public void deleteSeat(Integer seatId) {
        seatRepository.delete(Seat.builder().seatId(seatId).build());
    }

}
