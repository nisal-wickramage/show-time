package com.showtime.reservation.movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface MovieRepository  extends CrudRepository<Movie, Long> {
}
