package com.example.studyroom.service;

import com.example.studyroom.domain.Room;
import com.example.studyroom.domain.Seat;
import com.example.studyroom.domain.User;
import com.example.studyroom.dto.ChangeSeatDTO;
import com.example.studyroom.dto.ChoiceSeatDTO;
import com.example.studyroom.dto.EndSeatDTO;
import com.example.studyroom.dto.InsertSeatDTO;
import com.example.studyroom.repository.RoomRepository;
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
    private final RoomRepository roomRepository;

    public SeatService(SeatRepository seatRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
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
        isOccupied(seatRepository.findIsUsedBySeatId(choiceSeatDTO.getSeatId()));

        return updateSeat(choiceSeatDTO);
    }

    private void isUserUsing(Integer schoolId) {

        if (seatRepository.existsByUser(User.builder().schoolId(schoolId).build())) {
            throw new IllegalArgumentException("이미 선택한 자리가 있습니다!");
        }
    }

    private void isOccupied(Boolean isUsed) {

        if (isUsed) {
            throw new IllegalArgumentException("이미 사용하고 있는 자리입니다!");
        }
    }

    public Seat updateSeat(ChoiceSeatDTO choiceSeatDTO) {

        Seat seat = seatRepository.findById(choiceSeatDTO.getSeatId()).orElse(null);

        if (seat == null) {
            throw new IllegalArgumentException("좌석에 null값이 들어갔습니다");
        }

        Integer roomId = seatRepository.findRoomIdBySeatId(choiceSeatDTO.getSeatId());
        User user = userRepository.findBySchoolId(choiceSeatDTO.getSchoolId());

        if (!userRepository.existsBySchoolId(user.getSchoolId())) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다");
        }


        seat.choiceSeatUpdate(seatRepository.findSeatNumBySeatId(choiceSeatDTO.getSeatId()), userRepository.findBySchoolId(choiceSeatDTO.getSchoolId()), roomRepository.findByRoomId(roomId));
        seatRepository.save(seat);

        return seat;
    }


    public Seat endSeat(EndSeatDTO endSeatDTO) {

        isUsing(endSeatDTO);
        isEquals(endSeatDTO.getSchoolId(), endSeatDTO.getSeatId());

        Seat seat = seatRepository.findById(endSeatDTO.getSeatId()).orElse(null);

        if (seat == null) {
            throw new IllegalArgumentException("좌석에 null값이 들어갔습니다");
        }

        seat.endSeatUpdate();
        seatRepository.save(seat);

        return seat;
    }

    private void isUsing(EndSeatDTO endSeatDTO) {

        if (!endSeatDTO.getIsUsed()) {
            throw new IllegalArgumentException("이미 사용중이 아닌 좌석입니다");
        }
    }

    private void isEquals(Integer schoolId, Integer seatId) {
        if (!(seatRepository.findSeatIdByUserSchoolId(schoolId).equals(seatId))) {
            throw new IllegalArgumentException("취소하려는 좌석이 이전 좌석과 다릅니다");
        }
    }
}
