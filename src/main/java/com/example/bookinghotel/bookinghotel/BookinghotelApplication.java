package com.example.bookinghotel.bookinghotel;

import com.example.bookinghotel.bookinghotel.entity.Reservation;
import com.example.bookinghotel.bookinghotel.service.impl.ReservationServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BookinghotelApplication {

	@Autowired
	private static  ReservationServiceImpl reservationService;

	public static void main(String[] args) {

		SpringApplication.run(BookinghotelApplication.class, args);

	}




	@Bean
	CommandLineRunner runner (ReservationServiceImpl reservationService) {
		return args -> {
			System.out.println("***************Loading data from JSON***************");
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

			TypeReference<List<Reservation>> typeReference = new TypeReference<List<Reservation>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/reservations.json");
			try {
				List<Reservation> reservations = mapper.readValue(inputStream, typeReference);
				reservations.stream().forEach(r ->
						reservationService.save(r)
				);

				System.out.println("***************Data Loaded!***************");
			} catch (IOException e) {
				System.out.println("Unable to save data: " + e.getMessage());
			}
		};
	}
}
