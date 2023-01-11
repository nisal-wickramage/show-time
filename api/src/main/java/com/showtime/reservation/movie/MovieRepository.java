package com.showtime.reservation.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MovieRepository  extends JpaRepository<Movie, Long> {
}
