package com.showtime.reservation.reservation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    private final ReservationRepository _reservationRepository;

    private final ReservationService _reservationService;

    public ReservationController(
            ReservationRepository reservationRepository,
            ReservationService reservationService) {
        this._reservationRepository = reservationRepository;
        this._reservationService = reservationService;
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

    @PostMapping("/shows/{showId}/reservations")
    public ResponseEntity<Reservation> Create(@PathVariable("showId")long showId, @RequestBody Reservation reservationRequest) throws ReservationException {
        try {
            Reservation reservation = _reservationService.create(reservationRequest);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (ReservationException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
