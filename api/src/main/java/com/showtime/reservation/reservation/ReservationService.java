package com.showtime.reservation.reservation;

import org.springframework.stereotype.Component;

@Component
public class ReservationService {
    public Reservation create(Reservation reservation) throws ReservationException {
        throw new ReservationException("Not implemented");
    }
}
