package com.showtime.reservation.show;

import com.showtime.reservation.movie.Movie;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {

}
