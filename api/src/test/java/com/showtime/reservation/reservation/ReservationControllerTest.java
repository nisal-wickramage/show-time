package com.showtime.reservation.reservation;


import com.showtime.reservation.movie.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationRepository reservationRepository;

    @Test
    public void shouldReturn204WhenNoReservationsFound() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        when(reservationRepository.findByShowId(1)).thenReturn(reservations);
        ResultActions result = this.mockMvc.perform(get("/shows/1/reservations"));
        result.andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn200AndListOfReservationsWhenFound() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        reservations.add(new Reservation("John", "test@test.com", 1, seats));
        when(reservationRepository.findByShowId(1)).thenReturn(reservations);

        ResultActions result = this.mockMvc.perform(get("/shows/1/reservations"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()").value(1));
        result.andExpect(jsonPath("$.[0].seats.length()").value(1));
    }

    @Test
    public void shouldReturnSingleReservation() throws Exception {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);
        when(reservationRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(reservation));

        ResultActions result = this.mockMvc.perform(get("/reservations/1"));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void shouldReturnNoContentWhenReservationNotFound() throws Exception {
        when(reservationRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());

        ResultActions result = this.mockMvc.perform(get("/reservations/1"));

        result.andExpect(status().isNoContent());
    }
}
