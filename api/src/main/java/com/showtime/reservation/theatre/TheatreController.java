package com.showtime.reservation.theatre;

import com.showtime.reservation.movie.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TheatreController {
    private final TheatreRepository theatreRepository;

    public TheatreController(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @GetMapping("/theatres/{id}")
    public ResponseEntity<Theatre> Get(@PathVariable("id")long id) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(theatre.get(), HttpStatus.OK);
    }
}
