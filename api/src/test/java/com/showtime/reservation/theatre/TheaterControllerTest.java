package com.showtime.reservation.theatre;


import com.showtime.reservation.movie.Movie;
import com.showtime.reservation.movie.MovieRepository;
import com.showtime.reservation.show.Show;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TheatreController.class)
public class TheaterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheatreRepository theatreRepository;

    @Test
    public void shouldReturn200ForCorrectTheatre() throws Exception {
        Set<Seat> seats = new HashSet<>();
        seats.add(new Seat('A',1));
        seats.add(new Seat('A',2));
        seats.add(new Seat('B',1));
        seats.add(new Seat('B',2));
        Theatre theatre = new Theatre("IMAX", "IMAX", seats);

        when(theatreRepository.findById(any())).thenReturn(Optional.of(theatre));
        ResultActions result = this.mockMvc.perform(get("/theatres/1"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value("IMAX"));
        result.andExpect(jsonPath("$.code").value("IMAX"));
        result.andExpect(jsonPath("$.seats.length()").value(4));
    }

    @Test
    public void shouldReturn400ForInCorrectTheatreIds() throws Exception {
        when(theatreRepository.findById(any())).thenReturn(Optional.empty());
        ResultActions result = this.mockMvc.perform(get("/theatres/2"));
        result.andExpect(status().isBadRequest());
    }
}
