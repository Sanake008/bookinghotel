package com.example.bookinghotel.bookinghotel.service.impl;

import com.example.bookinghotel.bookinghotel.entity.Reservation;
import com.example.bookinghotel.bookinghotel.service.ReservationService;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
public class ReservationServiceImpl implements ReservationService {

    @Getter
    private Set<Reservation> reservations = new HashSet<>();

    @Override
    public Reservation save(Reservation reservation) {
        log.info("Saving new reservation!!");
        reservations.add(reservation);
        log.info(reservation);
        return reservation;
    }

    @Override
    public Reservation edit(Reservation reservation) {
        log.info("Editing reservation!!");
        Iterator<Reservation> iterator = reservations.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getId().equals(reservation.getId())) {
                iterator.remove();
                reservations.add(reservation);
                break;
            }
        }
        return  reservation;
    }

    @Override
    public Reservation findById(Reservation reservation) {
        log.info("Finding reservation by Id!!");
        Iterator<Reservation> iterator = reservations.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getId().equals(reservation.getId())) {
                return iterator.next();
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        log.info("Getting all the reservations!!");
        return reservations.stream().toList();
    }

    @Override
    public void delete(Reservation reservation) {
        Iterator<Reservation> iterator = reservations.iterator();
        log.info(reservations.size());
        while(iterator.hasNext()) {
            if(iterator.next().getId().equals(reservation.getId())) {
                log.info("Delete reservation from list!!");
                iterator.remove();
                log.info("Reservation deleted!!");
                break;
            }
        }
    }

    @EventListener({ ContextClosedEvent.class })
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(reservations.size());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("set", reservations);
        log.info(jsonObject.toString());
        File yourFile = new File("reservations.json");
        try {
            yourFile.createNewFile();
            FileOutputStream oFile = new FileOutputStream(yourFile, true);
            oFile.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            oFile.close();
            log.info("File saved"+yourFile.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
