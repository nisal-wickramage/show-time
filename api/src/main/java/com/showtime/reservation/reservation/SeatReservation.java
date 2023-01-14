package com.showtime.reservation.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
public class SeatReservation {
    protected SeatReservation(){}

    public SeatReservation(char rowId, int columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatReservation that = (SeatReservation) o;
        return rowId == that.rowId && columnId == that.columnId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowId, columnId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    private char rowId;

    @Getter
    private int columnId;

    @Setter
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="reservation_id")
    private Reservation reservation;
}
