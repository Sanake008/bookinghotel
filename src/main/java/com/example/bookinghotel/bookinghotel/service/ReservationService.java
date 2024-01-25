package com.example.bookinghotel.bookinghotel.service;

import com.example.bookinghotel.bookinghotel.entity.Reservation;

import java.util.List;

public interface ReservationService {

    public Reservation save(Reservation reservation);
    public Reservation edit(Reservation reservation);
    public Reservation findById(Reservation reservation);
    public List<Reservation>getAll();
    public void delete(Reservation reservation);


}
