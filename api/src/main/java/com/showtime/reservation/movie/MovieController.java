package com.showtime.reservation.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MovieController {

    @RequestMapping("/movies")
    public ResponseEntity<List<Movie>> greeting() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
