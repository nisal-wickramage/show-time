package com.showtime.reservation.reservation;

import com.showtime.reservation.show.Show;
import com.showtime.reservation.show.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    private ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ShowRepository showRepository;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepository, showRepository);
    }

    @Test
    public void shouldThrowExceptionIfShowIdIsIncorrect() {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);
        when(showRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservationService.create(reservation))
                .isInstanceOf(ReservationException.class)
                .hasMessage(ReservationExceptionMessages.showNotFound);
    }

    @Test
    public void shouldThrowExceptionIfNoSeatsSelected() {
        Set<SeatReservation> seats = new HashSet<>();
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);

        assertThatThrownBy(() -> reservationService.create(reservation))
                .isInstanceOf(ReservationException.class)
                .hasMessage(ReservationExceptionMessages.noSeatsSelected);
    }

    @Test
    public void shouldThrowExceptionIfNameIsEmpty() {
        Set<SeatReservation> seats = new HashSet<>();
        Reservation reservation = new Reservation("", "test@test.com", 1, seats);

        assertThatThrownBy(() -> reservationService.create(reservation))
                .isInstanceOf(ReservationException.class)
                .hasMessage(ReservationExceptionMessages.nameIsEmpty);
    }

    @Test
    public void shouldThrowExceptionIfSeatsAreAlreadyReserved() {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);
        Show show = new Show(1,1, LocalDateTime.of(2023,1,1,10,0), LocalDateTime.of(2023,1,1,12,0));
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        when(showRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(show));
        when(reservationRepository.findByShowId(Long.valueOf(1))).thenReturn(reservations);

        assertThatThrownBy(() -> reservationService.create(reservation))
                .isInstanceOf(ReservationException.class)
                .hasMessage(ReservationExceptionMessages.seatsAlreadyReserved);
    }

    @Test
    public void shouldReturnReservationIfValid() throws ReservationException {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);
        Show show = new Show(1,1, LocalDateTime.of(2023,1,1,10,0), LocalDateTime.of(2023,1,1,12,0));
        List<Reservation> reservations = new ArrayList<>();

        when(showRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(show));
        when(reservationRepository.findByShowId(Long.valueOf(1))).thenReturn(reservations);
        when(reservationRepository.save(any())).thenReturn(reservation);

        Reservation result = reservationService.create(reservation);

        assertThat(result.getEmail()).isEqualTo(reservation.getEmail());
        assertThat(result.getName()).isEqualTo(reservation.getName());
    }
}
