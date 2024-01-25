package com.example.studyroom.service;

import com.example.studyroom.Message;
import com.example.studyroom.domain.Room;
import com.example.studyroom.domain.Seat;
import com.example.studyroom.domain.User;
import com.example.studyroom.dto.*;
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
        isNotUse(seatRepository.findIsUsedBySeatId(choiceSeatDTO.getSeatId()));


        Room room = roomRepository.findByRoomId(seatRepository.findRoomIdBySeatId(choiceSeatDTO.getSeatId()));
        room.useSeat();
        roomRepository.save(room);

        return useSeat(choiceSeatDTO.getSeatId(), choiceSeatDTO.getSchoolId());
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

        Seat seat = seatRepository.findById(seatId).orElse(null);

        if (seat == null) {
            throw new NullPointerException(Message.INCORRECT_SEAT.getMessage());
        }

        Integer roomId = seatRepository.findRoomIdBySeatId(seatId);
        User user = userRepository.findBySchoolId(schoolId);

        if (!userRepository.existsBySchoolId(user.getSchoolId())) {
            throw new IllegalArgumentException(Message.NON_EXISTENT_USER.getMessage());
        }

        seat.choiceSeatUpdate(seatRepository.findSeatNumBySeatId(seatId), userRepository.findBySchoolId(schoolId), roomRepository.findByRoomId(roomId));
        seatRepository.save(seat);

        return seat;
    }

    public Seat cancleSeat(CancleSeatDTO cancleSeatDTO) {

        Room room = roomRepository.findByRoomId(seatRepository.findRoomIdBySeatId(cancleSeatDTO.getSeatId()));
        room.endSeat();
        roomRepository.save(room);

        return endSeat(seatRepository.findIsUsedBySeatId(cancleSeatDTO.getSeatId()), cancleSeatDTO.getSeatId(), cancleSeatDTO.getSchoolId());
    }

    private Seat endSeat(Boolean isUsed, Integer seatId, Integer schoolId) {

        isUsing(!isUsed);
        isEquals(schoolId, seatId);

        Seat seat = seatRepository.findById(seatId).orElse(null);

        if (seat == null) {
            throw new NullPointerException(Message.INCORRECT_SEAT.getMessage());
        }

        seat.endSeatUpdate();
        seatRepository.save(seat);

        return seat;
    }

    private void isUsing(Boolean isUsed) {

        if (isUsed) {
            throw new IllegalArgumentException(Message.NON_EXISTENT_USER.getMessage());
        }
    }

    private void isEquals(Integer schoolId, Integer seatId) {

        if (!(seatRepository.findSeatIdByUserSchoolId(schoolId).equals(seatId))) {
            throw new IllegalArgumentException(Message.DIFFERENT_PREVIOUS_SEAT.getMessage());
        }
    }

    public Seat changeSeat(ChangeSeatDTO changeSeatDTO) {

        isNotUse(seatRepository.findIsUsedBySeatId(changeSeatDTO.getSeatId()));
        isSeatDifferent(changeSeatDTO.getSeatId(), changeSeatDTO.getSchoolId());

        Boolean existingSeatIsUsed = seatRepository.findIsUsedBySeatId(seatRepository.findSeatIdByUserSchoolId(changeSeatDTO.getSchoolId()));
        Integer existingSeatId = seatRepository.findSeatIdByUserSchoolId(changeSeatDTO.getSchoolId());

        endSeat(existingSeatIsUsed, existingSeatId, changeSeatDTO.getSchoolId());

        Seat newSeat = useSeat(changeSeatDTO.getSeatId(), changeSeatDTO.getSchoolId());

        return newSeat;
    }

    private void isSeatDifferent(Integer seatId, Integer schoolId) {

        if ((seatRepository.findSeatIdByUserSchoolId(schoolId)).equals(seatId)) {
            throw new IllegalArgumentException(Message.SAME_PREVIOUS_SEAT.getMessage());
        }
    }

    public Seat extendSeat(ExtendSeatDTO extendSeatDTO) {
        isUsing(!seatRepository.findIsUsedBySeatId(extendSeatDTO.getSeatId()));
        isEquals(extendSeatDTO.getSchoolId(), extendSeatDTO.getSeatId());

        Seat seat = seatRepository.findById(extendSeatDTO.getSeatId()).orElse(null);

        if (seat == null) {
            throw new NullPointerException(Message.INCORRECT_SEAT.getMessage());
        }

        seat.extendSeat();
        seatRepository.save(seat);

        return seat;
    }
}
