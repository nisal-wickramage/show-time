package com.showtime.reservation.reservation;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
public class Reservation {
    protected Reservation(){}

    public Reservation(String name, String email, long showId, Set<SeatReservation> seats) {
        this.name = name;
        this.email = email;
        this.showId = showId;
        for (SeatReservation seat: seats) {
            seat.setReservation(this);
        }
        this.seats = seats;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    private long showId;


    @Getter
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<SeatReservation> seats;

}
