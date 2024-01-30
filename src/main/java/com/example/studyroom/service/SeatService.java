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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional // 자동으로 db의 변경 감지
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
                .seatNum(insertSeatDTO.getSeatId())
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

    public Seat choiceSeat(ChoiceSeatDTO choiceSeatDTO) {

        isUserUsing(choiceSeatDTO.getSchoolId());
        isNotUse(seatRepository.findIsUsedBySeatId(choiceSeatDTO.getSeatId()));

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

        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new NullPointerException(Message.INCORRECT_SEAT.getMessage()));

        Integer roomId = seatRepository.findRoomIdBySeatId(seatId);
        User user = userRepository.findBySchoolId(schoolId);

        if (!userRepository.existsBySchoolId(user.getSchoolId())) {
            throw new IllegalArgumentException(Message.NON_EXISTENT_USER.getMessage());
        }

        seat.choiceSeatUpdate(seatRepository.findSeatNumBySeatId(seatId), userRepository.findBySchoolId(schoolId), roomRepository.findByRoomId(roomId));
        seatRepository.save(seat);

        user.useSeat(seat);
        userRepository.save(user);

        return seat;
    }

    public Seat cancelSeat(CancelSeatDTO cancelSeatDTO) {

        Seat seat = seatRepository.findById(cancelSeatDTO.getSeatId()).get();
        User user = userRepository.findBySchoolId(cancelSeatDTO.getSchoolId());

        return endSeat(seat, user);
    }

    private Seat endSeat(Seat seat, User user) {

        user.endSeat();

        seat.endSeatUpdate();
        seatRepository.save(seat);

        return seat;
    }

    private void isEquals(Integer schoolId, Integer seatId) {

        if (!(seatRepository.findSeatIdByUserSchoolId(schoolId).equals(seatId))) {
            throw new IllegalArgumentException(Message.DIFFERENT_PREVIOUS_SEAT.getMessage());
        }
    }

    public Seat changeSeat(ChangeSeatDTO changeSeatDTO) {

        isNotUse(seatRepository.findIsUsedBySeatId(changeSeatDTO.getSeatId()));
        isSeatDifferent(changeSeatDTO.getSeatId(), changeSeatDTO.getSchoolId());

        Seat seat = seatRepository.findBySeatId(seatRepository.findSeatIdByUserSchoolId(changeSeatDTO.getSchoolId()));
        User user = userRepository.findBySchoolId(changeSeatDTO.getSchoolId());

        endSeat(seat, user);

        Seat newSeat = useSeat(changeSeatDTO.getSeatId(), changeSeatDTO.getSchoolId());

        return newSeat;
    }

    private void isSeatDifferent(Integer seatId, Integer schoolId) {

        if ((seatRepository.findSeatIdByUserSchoolId(schoolId)).equals(seatId)) {
            throw new IllegalArgumentException(Message.SAME_PREVIOUS_SEAT.getMessage());
        }
    }

    public Seat extendSeat(ExtendSeatDTO extendSeatDTO) {
        isEquals(extendSeatDTO.getSchoolId(), extendSeatDTO.getSeatId());

        Seat seat = seatRepository.findById(extendSeatDTO.getSeatId()).orElseThrow(() -> new NullPointerException(Message.INCORRECT_SEAT.getMessage()));

        seat.extendSeat();
        seatRepository.save(seat);

        return seat;
    }

    public void endExpiredSeats() {
        List<Integer> expiredSeatList = seatRepository.findExpiredSeats();

        for (Integer seatId : expiredSeatList) {

            User user = userRepository.findBySchoolId(seatRepository.findSchoolIdBySeatId(seatId));
            Seat seat = seatRepository.findById(seatId).get();
            endSeat(seat, user);
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
