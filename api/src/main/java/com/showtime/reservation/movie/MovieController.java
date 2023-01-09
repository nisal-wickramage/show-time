package com.showtime.reservation.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    private final MovieRepository _movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this._movieRepository = movieRepository;
    }

    @RequestMapping("/movies")
    public ResponseEntity<List<Movie>> greeting() {
        List<Movie> movies = new ArrayList<>();
        _movieRepository.findAll().iterator().forEachRemaining(movies::add);

        if(movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
