package com.showtime.reservation.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn204WhenNoMoviesAreFound() throws Exception {
//        this.mockMvc.perform(get("/movies")).andExpect(status().isNoContent())
//                .andExpect(content().string(containsString("Hello, Mock")));
        this.mockMvc.perform(get("/movies")).andExpect(status().isNoContent());
    }
}
