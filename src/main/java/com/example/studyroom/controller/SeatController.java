package com.example.studyroom.controller;

import com.example.studyroom.domain.Seat;
import com.example.studyroom.dto.ChangeSeatDTO;
import com.example.studyroom.dto.ChoiceSeatDTO;
import com.example.studyroom.dto.CancleSeatDTO;
import com.example.studyroom.dto.InsertSeatDTO;
import com.example.studyroom.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
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

    @PatchMapping("/cancle")
    public Seat cancleSeat(@RequestBody CancleSeatDTO cancleSeatDTO) {
        return seatService.cancleSeat(cancleSeatDTO);
    }
}
