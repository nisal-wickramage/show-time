package com.showtime.reservation.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private final MovieRepository _movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this._movieRepository = movieRepository;
    }

    @GetMapping ("/movies")
    public ResponseEntity<List<Movie>> Get() {
       List<Movie> movies = _movieRepository.findAll();
       if(movies.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
