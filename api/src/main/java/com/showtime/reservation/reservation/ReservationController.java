package com.showtime.reservation.reservation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    private final ReservationRepository _reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this._reservationRepository = reservationRepository;
    }

    @GetMapping("/shows/{showId}/reservations")
    public ResponseEntity<List<Reservation>> GetAll(@PathVariable("showId")long showId) {
        List<Reservation> reservations = _reservationRepository.findByShowId(showId);
        if (reservations.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<Reservation> Get(@PathVariable("reservationId")long reservationId) {
        Optional<Reservation> reservation = _reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
    }
}
