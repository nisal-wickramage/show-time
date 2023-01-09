package com.showtime.reservation.show;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
public class Show {

    protected Show() {
    }

    public Show(long movieId, long theatreId, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    private long movieId;

    @Getter
    private long theatreId;

    @Getter
    private LocalDateTime startDatetime;

    @Getter
    private LocalDateTime endDatetime;
}
