package com.example.airline_reservation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stops")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Stop {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Integer stopId;

    String stopName;

    LocalDateTime arrivalDateTime, departureDateTime;

    @EmbeddedId
    private StopId stopId;

    @OneToMany(mappedBy = "stop")
    List<Seat> seats;
}
