package com.showtime.reservation.reservation;


import com.showtime.reservation.movie.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ResultActions result = this.mockMvc.perform(get("/movies/1/shows/1/reservations"));
        result.andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn200AndListOfReservationsWhenFound() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        reservations.add(new Reservation("John", "test@test.com", 1, seats));

        when(reservationRepository.findByShowId(1)).thenReturn(reservations);
        ResultActions result = this.mockMvc.perform(get("/movies/1/shows/1/reservations"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()").value(1));
        result.andExpect(jsonPath("$.[0].seats.length()").value(1));
    }
}
