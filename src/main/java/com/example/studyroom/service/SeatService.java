package com.example.studyroom.service;

import com.example.studyroom.domain.*;
import com.example.studyroom.dto.*;
import com.example.studyroom.repository.*;
import jakarta.validation.constraints.Null;
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
            throw new IllegalArgumentException("이미 선택한 자리가 있습니다!");
        }
    }

    private void isNotUse(Boolean isUsed) {

        if (isUsed) {
            throw new IllegalArgumentException("이미 사용하고 있는 자리입니다!");
        }
    }

    public Seat useSeat(Integer seatId, Integer schoolId) {

        Seat seat = seatRepository.findById(seatId).orElse(null);

        if (seat == null) {
            throw new NullPointerException("좌석에 null값이 들어갔습니다");
        }

        Integer roomId = seatRepository.findRoomIdBySeatId(seatId);
        User user = userRepository.findBySchoolId(schoolId);

        if (!userRepository.existsBySchoolId(user.getSchoolId())) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다");
        }

        seat.choiceSeatUpdate(seatRepository.findSeatNumBySeatId(seatId), userRepository.findBySchoolId(schoolId), roomRepository.findByRoomId(roomId));
        seatRepository.save(seat);

        return seat;
    }

    public Seat cancleSeat(CancleSeatDTO cancleSeatDTO){

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
            throw new NullPointerException("좌석이 없습니다");
        }

        seat.endSeatUpdate();
        seatRepository.save(seat);

        return seat;
    }

    private void isUsing(Boolean isUsed) {

        if (isUsed) {
            throw new IllegalArgumentException("이미 사용중이 아닌 좌석입니다");
        }
    }

    private void isEquals(Integer schoolId, Integer seatId) {

        if (!(seatRepository.findSeatIdByUserSchoolId(schoolId).equals(seatId))) {
            throw new IllegalArgumentException("좌석이 이전 좌석과 다릅니다");
        }
    }

    public Seat changeSeat(ChangeSeatDTO changeSeatDTO) {

        isNotUse(seatRepository.findIsUsedBySeatId(changeSeatDTO.getSeatId()));
        isSeatDifferent(changeSeatDTO.getSeatId(), changeSeatDTO.getSchoolId());

        Boolean existingSeatIsUsed = seatRepository.findIsUsedBySeatId(seatRepository.findSeatIdByUserSchoolId(changeSeatDTO.getSchoolId()));
        Integer existingSeatId = seatRepository.findSeatIdByUserSchoolId(changeSeatDTO.getSchoolId());

        endSeat(existingSeatIsUsed, existingSeatId,  changeSeatDTO.getSchoolId());

        Seat newSeat = useSeat(changeSeatDTO.getSeatId(), changeSeatDTO.getSchoolId());

        return newSeat;
    }

    private void isSeatDifferent(Integer seatId, Integer schoolId) {

        if ((seatRepository.findSeatIdByUserSchoolId(schoolId)).equals(seatId)) {
            throw new IllegalArgumentException("변경하려는 좌석이 이전 좌석과 동일합니다");
        }
    }

    public Seat extendSeat(ExtendSeatDTO extendSeatDTO){
        isUsing(!seatRepository.findIsUsedBySeatId(extendSeatDTO.getSeatId()));
        isEquals(extendSeatDTO.getSchoolId(), extendSeatDTO.getSeatId());

        Seat seat = seatRepository.findById(extendSeatDTO.getSeatId()).orElse(null);

        if(seat == null){
            throw new NullPointerException("올바르지 않은 좌석입니다");
        }

        seat.extendSeat();
        seatRepository.save(seat);

        return seat;
    }
}
