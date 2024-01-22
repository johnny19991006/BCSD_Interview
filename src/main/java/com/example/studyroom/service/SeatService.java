package com.example.studyroom.service;

import com.example.studyroom.domain.Room;
import com.example.studyroom.domain.Seat;
import com.example.studyroom.domain.User;
import com.example.studyroom.dto.ChoiceSeatDTO;
import com.example.studyroom.dto.InsertSeatDTO;
import com.example.studyroom.repository.SeatRepository;
import com.example.studyroom.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Seat choiceSeat(ChoiceSeatDTO choiceSeatDTO) {

        isUserUsing(choiceSeatDTO.getSchoolId());
        isOccupied(choiceSeatDTO.getIsUsed());

        return updateSeat(choiceSeatDTO);
    }

    private void isUserUsing(Integer schoolId) {
        if (seatRepository.existsByUser(User.builder().schoolId(schoolId).build())) {
            throw new IllegalArgumentException("이미 선택한 자리가 있습니다!");
        }
    }

    private void isOccupied(boolean isUsed) {
        if (isUsed) {
            throw new IllegalArgumentException("이미 사용하고 있는 자리입니다!");
        }
    }

    public Seat updateSeat(ChoiceSeatDTO choiceSeatDTO) {
        LocalDateTime now = LocalDateTime.now();

        Seat seat = seatRepository.findById(choiceSeatDTO.getSeatId()).orElse(null);

        if (seat != null) {
            seat.setSeatNum(choiceSeatDTO.getSeatNum());
            seat.setIsUsed(true);
            seat.setUser(User.builder().schoolId(choiceSeatDTO.getSchoolId()).build());
            seat.setRoom(Room.builder().roomId(choiceSeatDTO.getRoomId()).build());
            seat.setStartTime(now);
            seat.setEndTime(now.plusHours(3));

            seatRepository.save(seat);
            return seat;
        }

        return null;
    }

}
