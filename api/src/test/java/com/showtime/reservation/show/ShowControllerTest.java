package com.showtime.reservation.show;


import com.showtime.reservation.movie.Movie;
import com.showtime.reservation.movie.MovieRepository;
import com.showtime.reservation.show.ShowController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShowController.class)
public class ShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowRepository showRepository;

    @Test
    public void shouldReturn204WhenNoMoviesAreFound() throws Exception {
        when(showRepository.findByMovieId(1)).thenReturn(new ArrayList<Show>());
        this.mockMvc.perform(get("/movies/1/shows")).andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnShowsWhenFound() throws Exception {
        List<Show> shows = new ArrayList<>();
        shows.add(new Show(
                1,
                1,
                LocalDateTime.of(2023,1,1,10,0),
                LocalDateTime.of(2023,1,1,12,0)));

        when(showRepository.findByMovieId(1)).thenReturn(shows);
        ResultActions result = this.mockMvc.perform(get("/movies/1/shows"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()").value(1));
    }


}
