package com.example.airline_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer seatId;

    @Enumerated(EnumType.STRING)
    SeatClass seatClass;
    double fare;
    int availableSeats;
    int totalSeats;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "stop_number", referencedColumnName = "stopNumber"), @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")})
    Stop stop;
}
