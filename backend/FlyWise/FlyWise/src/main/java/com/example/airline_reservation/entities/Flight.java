package com.example.airline_reservation.entities;


//import jakarta.persistence.*;
//import java/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.List;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer flightId;
    String flightName;
    @Lob
    byte[] logo;

    @OneToMany(mappedBy = "stopId.flight")
    List<Stop> stops;
}
