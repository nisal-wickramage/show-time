package com.showtime.reservation.reservation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationRepository _reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this._reservationRepository = reservationRepository;
    }

    @GetMapping("/movies/{movieId}/shows/{showId}/reservations")
    public ResponseEntity<List<Reservation>> Get(@PathVariable("movieId")long movieId, @PathVariable("showId")long showId) {
        List<Reservation> reservations = _reservationRepository.findByShowId(showId);
        if (reservations.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
