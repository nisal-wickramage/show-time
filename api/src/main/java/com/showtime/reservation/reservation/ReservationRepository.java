package com.showtime.reservation.reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository  extends CrudRepository<Reservation, Long> {
}
