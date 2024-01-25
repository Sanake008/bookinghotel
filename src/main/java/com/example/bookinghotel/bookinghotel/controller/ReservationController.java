package com.example.bookinghotel.bookinghotel.controller;

import com.example.bookinghotel.bookinghotel.entity.Reservation;
import com.example.bookinghotel.bookinghotel.service.impl.ReservationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/save")
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

    @PutMapping("/edit")
    public  Reservation edit(@RequestBody Reservation reservation){
        return reservationService.edit(reservation);
    }

    @GetMapping("getById")
    public Reservation getById(@RequestBody Reservation reservation){
        return  reservationService.findById(reservation);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Reservation reservation){
        reservationService.delete(reservation);
    }


}
