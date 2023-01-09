package com.showtime.reservation.theatre;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Entity
public class Theatre {
    protected    Theatre() {}

    public Theatre(String name, String code, Set<Seat> seats) {
        this.name = name;
        this.code = code;

        for (Seat seat:seats) {
            seat.setTheatre(this);
        }
        this.seats = seats;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    private String name;

    @Getter
    private String code;

    @Getter
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private Set<Seat> seats;
}
