package com.example.bookinghotel.bookinghotel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Reservation {
    private Integer id;
    private String clientFullName;
    private Integer roomNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private List<LocalDate> reservationDates;
}
