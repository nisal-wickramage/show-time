package com.showtime.reservation.theatre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Seat {
    protected Seat(){}

    public Seat(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    private int rowNumber;

    @Getter
    private int columnNumber;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="theatre_id")
    private Theatre theatre;

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
