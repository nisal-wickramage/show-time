package com.showtime.reservation.movie;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void shouldReturn204WhenNoMoviesAreFound() throws Exception {
        when(movieRepository.findAll()).thenReturn(new ArrayList<Movie>());
        this.mockMvc.perform(get("/movies")).andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn200AndListOfMoviesWhenFound() throws Exception {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avengers", "Infinity War", "INF", "2:30:30", "https://localhost"));
        when(movieRepository.findAll()).thenReturn(movies);
        ResultActions result = this.mockMvc.perform(get("/movies"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.length()").value(1));
        result.andExpect(jsonPath("$.[0].title").value("Avengers"));
    }
}
