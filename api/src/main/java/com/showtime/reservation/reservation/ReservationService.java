package com.showtime.reservation.reservation;

import com.showtime.reservation.show.Show;
import com.showtime.reservation.show.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final ReservationRepository _reservationRepository;
    private final ShowRepository _showRepository;
    public ReservationService(ReservationRepository reservationRepository, ShowRepository showRepository) {
        this._reservationRepository = reservationRepository;
        this._showRepository = showRepository;
    }

    public Reservation create(Reservation reservation) throws ReservationException {
        if (reservation.getName().isEmpty()) {
            throw new ReservationException(ReservationExceptionMessages.nameIsEmpty);
        }
        if (reservation.getSeats() == null || reservation.getSeats().size() < 1) {
            throw new ReservationException(ReservationExceptionMessages.noSeatsSelected);
        }

        Optional<Show> show = _showRepository.findById(reservation.getShowId());
        if (show.isEmpty()) {
            throw new ReservationException(ReservationExceptionMessages.showNotFound);
        }

        List<Reservation> reservations = _reservationRepository.findByShowId(reservation.getShowId());
        if(reservations.size() > 0) {
            List<SeatReservation> reservedSeats = new ArrayList<>();
            reservations.forEach(res -> reservedSeats.addAll(res.getSeats()));
            boolean matchFound = reservedSeats.stream().filter(seat -> reservation.getSeats().stream().anyMatch(requestSeat -> requestSeat.equals(seat))).count() > 0;
            if (matchFound){
                throw new ReservationException(ReservationExceptionMessages.seatsAlreadyReserved);
            }
        }
        return _reservationRepository.save(reservation);
    }
}
