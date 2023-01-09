package com.showtime.reservation.theatre;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Seat {
    protected Seat(){}

    public Seat(char rowId, int columnId) {
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

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="theatre_id")
    private Theatre theatre;

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
