package com.showtime.reservation.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class SeatReservation {
    protected SeatReservation(){}

    public SeatReservation(char rowId, int columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    private char rowId;

    @Getter
    private int columnId;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="reservation_id")
    private Reservation reservation;


    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
