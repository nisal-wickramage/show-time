package com.showtime.reservation.theatre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="theatre_id")
    private Theatre theatre;
}
