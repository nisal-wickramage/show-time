package com.showtime.reservation;

import com.showtime.reservation.movie.Movie;
import com.showtime.reservation.movie.MovieRepository;
import com.showtime.reservation.reservation.Reservation;
import com.showtime.reservation.reservation.ReservationRepository;
import com.showtime.reservation.reservation.SeatReservation;
import com.showtime.reservation.show.Show;
import com.showtime.reservation.show.ShowRepository;
import com.showtime.reservation.theatre.Seat;
import com.showtime.reservation.theatre.Theatre;
import com.showtime.reservation.theatre.TheatreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner demo(MovieRepository repository) {
//		return (args) -> {
//			repository.save(new Movie(
//					"Avengers",
//					"Infinity War",
//					"AIW",
//					"1:40:00",
//					"https://localhost"));
//		};
//	}

//	@Bean
//	public CommandLineRunner demo(ShowRepository repository) {
//		return (args) -> {
//			repository.save(new Show(
//					1,
//					1,
//					LocalDateTime.of(2023,1,1,1,1),
//					LocalDateTime.of(2023,1,1,1,2)
//			));
//		};
//	}

//

//	@Bean
//	public CommandLineRunner demo(TheatreRepository repository) {
//		return (args) -> {
//			Set<Seat> seats = new HashSet<>();
//			seats.add(new Seat('A',1));
//			seats.add(new Seat('A',2));
//			Theatre theatre = new Theatre("IMAX-01", "IMAX-01", seats);
//
//			repository.save(theatre);
//		};
//	}

//	@Bean
//	public CommandLineRunner demo(ReservationRepository repository) {
//		return (args) -> {
//			Set<SeatReservation> seats = new HashSet<>();
//			seats.add(new SeatReservation('A',1));
//			seats.add(new SeatReservation('A',2));
//			Reservation reservation = new Reservation("Nisal", "nnnnnnuw@gmail.com",1, seats);
//
//			repository.save(reservation);
//		};
//	}
}
