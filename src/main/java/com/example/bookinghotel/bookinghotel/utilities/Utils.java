package com.example.bookinghotel.bookinghotel.utilities;

import com.example.bookinghotel.bookinghotel.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Utils {

    public void printSet(Set<Reservation>set){
        System.out.println(set);
    }

}
