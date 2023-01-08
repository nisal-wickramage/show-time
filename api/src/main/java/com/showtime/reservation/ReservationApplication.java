package com.showtime.reservation;

import com.showtime.reservation.movie.Movie;
import com.showtime.reservation.movie.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(MovieRepository repository) {
		return (args) -> {
			repository.save(new Movie(
					"Avengers",
					"Infinity War",
					"AIW",
					"1:40:00",
					"https://localhost"));
		};
	}
}