package com.showtime.reservation.show;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowRepository extends CrudRepository<Show, Long> {
    List<Show> findByMovieId(long movieId);
}
