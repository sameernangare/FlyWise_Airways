package com.example.airline_reservation.repository;

import com.example.airline_reservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDaoImpl extends JpaRepository<Flight, Integer> {

}
