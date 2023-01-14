package com.showtime.reservation.show;


import com.showtime.reservation.movie.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {

    private final ShowRepository _showRepository;

    public ShowController(ShowRepository showRepository) {
        this._showRepository = showRepository;
    }

    @GetMapping("/movies/{id}/shows")
    public ResponseEntity<List<Show>> Get(@PathVariable("id")long movieId) {
        List<Show> shows = _showRepository.findByMovieId(movieId);

        if (shows.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shows, HttpStatus.OK);
    }
}
