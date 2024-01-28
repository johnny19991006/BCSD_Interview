package com.example.studyroom.controller;

import com.example.studyroom.domain.Seat;
import com.example.studyroom.dto.*;
import com.example.studyroom.service.SeatService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@EnableScheduling
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("")
    public Seat insertSeat(@RequestBody InsertSeatDTO insertSeatDTO) {
        return seatService.insertSeat(insertSeatDTO);
    }

    @GetMapping("")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{seatId}")
    public Seat getSeatBySeatId(@PathVariable int seatId) {
        return seatService.getSeatBySeatID(seatId);
    }

    @DeleteMapping("/{seatId}")
    public void deleteSeat(@PathVariable int seatId) {
        seatService.deleteSeat(seatId);
    }

    @PatchMapping("/")
    public Seat choiceSeat(@RequestBody ChoiceSeatDTO choiceSeatDTO) {
        return seatService.choiceSeat(choiceSeatDTO);
    }

    @PatchMapping("/change")
    public Seat changeSeat(@RequestBody ChangeSeatDTO changeSeatDTO){
        return seatService.changeSeat(changeSeatDTO);
    }

    @PatchMapping("/cancle")
    public Seat cancleSeat(@RequestBody CancleSeatDTO cancleSeatDTO) {
        return seatService.cancleSeat(cancleSeatDTO);
    }

    @PatchMapping("/extend")
    public Seat extendSeat(@RequestBody ExtendSeatDTO extendSeatDTO){
        return seatService.extendSeat(extendSeatDTO);
    }

    @Scheduled(fixedRate = 10000)
    public void endExpiredSeat(){
        seatService.endExpiredSeats();
        seatService.updateRoom();
    }
}
