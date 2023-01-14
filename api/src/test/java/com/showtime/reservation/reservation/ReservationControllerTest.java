package com.showtime.reservation.reservation;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationRepository reservationRepository;
    @MockBean
    private ReservationService reservationService;

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

    @Test
    public void shouldReturnCreatedForValidRequest() throws Exception {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 10, seats);

        when(reservationService.create(any())).thenReturn(reservation);

        ResultActions result = this.mockMvc.perform(post("/shows/1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservation)));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.name").value("John"));
        result.andExpect(jsonPath("$.seats.length()").value(1));
    }

    @Test
    public void shouldReturnBadRequestIfShowIdIsInvalid() throws Exception {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);

        when(reservationService.create(any())).thenThrow(new ReservationException(ReservationExceptionMessages.showNotFound));

        ResultActions result = this.mockMvc.perform(post("/shows/1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservation)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfNoSeatsSelected() throws Exception {
        Set<SeatReservation> seats = new HashSet<>();
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);

        when(reservationService.create(any())).thenThrow(new ReservationException(ReservationExceptionMessages.noSeatsSelected));

        ResultActions result = this.mockMvc.perform(post("/shows/1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservation)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfNameIsEmpty() throws Exception {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("", "test@test.com", 1, seats);

        when(reservationService.create(any())).thenThrow(new ReservationException(ReservationExceptionMessages.nameIsEmpty));

        ResultActions result = this.mockMvc.perform(post("/shows/1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservation)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestIfSeatsAreAlreadyBooked() throws Exception {
        Set<SeatReservation> seats = new HashSet<>();
        seats.add(new SeatReservation('A', 1));
        Reservation reservation = new Reservation("John", "test@test.com", 1, seats);

        when(reservationService.create(any())).thenThrow(new ReservationException(ReservationExceptionMessages.seatsAlreadyReserved));

        ResultActions result = this.mockMvc.perform(post("/shows/1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservation)));

        result.andExpect(status().isBadRequest());
    }
}
