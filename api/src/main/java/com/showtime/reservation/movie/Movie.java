package com.showtime.reservation.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Movie {
    public Movie() {}

    public Movie(String title, String description, String code, String runtime, String posterUrl) {
        this.title = title;
        this.description = description;
        this.code = code;
        this.runtime = runtime;
        this.posterUrl = posterUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private String code;

    @Getter
    private String runtime;

    @Getter
    private String posterUrl;
}
