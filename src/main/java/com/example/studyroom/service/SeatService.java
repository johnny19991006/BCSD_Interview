package com.example.studyroom.service;

import com.example.studyroom.Message;
import com.example.studyroom.domain.*;
import com.example.studyroom.dto.*;
import com.example.studyroom.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
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

        return seatRepository.findById(seatId).orElseThrow(() -> new NullPointerException(Message.INCORRECT_SEAT.getMessage()));
    }

    public void deleteSeat(Integer seatId) {

        seatRepository.delete(Seat.builder().seatId(seatId).build());
    }

    public Seat choiceSeat(UpdateSeatDTO updateSeatDTO, Integer schoolId) {

        isUserUsing(updateSeatDTO.getSchoolId());
        isNotUse(seatRepository.findIsUsedBySeatId(updateSeatDTO.getSeatId()));

        if(!Objects.equals(updateSeatDTO.getSchoolId(), schoolId)){
            throw new IllegalArgumentException(Message.DIFFERENT_USER.getMessage());
        }

        return useSeat(updateSeatDTO.getSeatId(), updateSeatDTO.getSchoolId());
    }

    private void isUserUsing(Integer schoolId) {

        if (seatRepository.existsByUser(User.builder().schoolId(schoolId).build())) {
            throw new IllegalArgumentException(Message.ALREADY_USE.getMessage());
        }
    }

    private void isNotUse(Boolean isUsed) {

        if (isUsed) {
            throw new IllegalArgumentException(Message.ALREADY_OCCUPIED.getMessage());
        }
    }

    public Seat useSeat(Integer seatId, Integer schoolId) {

        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new NullPointerException(Message.INCORRECT_SEAT.getMessage()));

        Integer roomId = seatRepository.findRoomIdBySeatId(seatId);

        seat.choiceSeatUpdate(seatRepository.findSeatNumBySeatId(seatId), userRepository.findBySchoolId(schoolId), roomRepository.findByRoomId(roomId));
        seatRepository.save(seat);

        return seat;
    }

    public Seat cancelSeat(UpdateSeatDTO updateSeatDTO, Integer schoolId) {

        Seat seat = seatRepository.findBySeatId(updateSeatDTO.getSeatId());

        if(!Objects.equals(seat.getUser().getSchoolId(), schoolId)){
            throw new IllegalArgumentException(Message.DIFFERENT_USER.getMessage());
        }

        return endSeat(seat);
    }

    private Seat endSeat(Seat seat) {

        seat.endSeatUpdate();
        seatRepository.save(seat);

        return seat;
    }


    public Seat changeSeat(UpdateSeatDTO updateSeatDTO, Integer schoolId) {

        isNotUse(seatRepository.findIsUsedBySeatId(updateSeatDTO.getSeatId()));
        isSeatDifferent(updateSeatDTO.getSeatId(), updateSeatDTO.getSchoolId());

        Seat seat = seatRepository.findBySeatId(seatRepository.findSeatIdByUserSchoolId(updateSeatDTO.getSchoolId()));

        if(!Objects.equals(seat.getUser().getSchoolId(), schoolId)){

            throw new IllegalArgumentException(Message.DIFFERENT_USER.getMessage());
        }

        endSeat(seat);

        Seat newSeat = useSeat(updateSeatDTO.getSeatId(), updateSeatDTO.getSchoolId());
        return newSeat;
    }

    private void isSeatDifferent(Integer seatId, Integer schoolId) {

        if ((seatRepository.findSeatIdByUserSchoolId(schoolId)).equals(seatId)) {
            throw new IllegalArgumentException(Message.SAME_PREVIOUS_SEAT.getMessage());
        }
    }

    public Seat extendSeat(UpdateSeatDTO updateSeatDTO, Integer schoolId) {
        isEquals(updateSeatDTO.getSchoolId(), updateSeatDTO.getSeatId());

        Seat seat = seatRepository.findById(updateSeatDTO.getSeatId()).orElseThrow(() -> new NullPointerException(Message.INCORRECT_SEAT.getMessage()));

        if(!Objects.equals(seat.getUser().getSchoolId(), schoolId)){
            throw new IllegalArgumentException(Message.DIFFERENT_USER.getMessage());
        }

        seat.extendSeat();
        seatRepository.save(seat);

        return seat;
    }

    private void isEquals(Integer schoolId, Integer seatId) {

        if (!(seatRepository.findSeatIdByUserSchoolId(schoolId).equals(seatId))) {
            throw new IllegalArgumentException(Message.DIFFERENT_PREVIOUS_SEAT.getMessage());
        }
    }

    public void endExpiredSeats() {
        List<Integer> expiredSeatList = seatRepository.findExpiredSeats();

        for (Integer seatId : expiredSeatList) {

            endSeat(seatRepository.findBySeatId(seatId));
        }
    }

    public void updateRoom() {
        List<Integer> roomList = seatRepository.findAllRoomId();

        for (Integer roomNumber : roomList) {
            Room room = roomRepository.findByRoomId(roomNumber);

            room.usedSeats(seatRepository.countByRoom_RoomIdAndIsUsed(roomNumber, true)); // 어떤 방의 사용중인 좌석
            roomRepository.save(room);
        }
    }
}
