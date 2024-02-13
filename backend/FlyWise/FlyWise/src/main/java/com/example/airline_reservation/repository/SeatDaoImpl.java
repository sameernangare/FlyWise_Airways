package com.example.airline_reservation.repository;

import com.example.airline_reservation.entities.Seat;
import com.example.airline_reservation.entities.SeatClass;
import com.example.airline_reservation.entities.StopId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatDaoImpl extends JpaRepository<Seat, Integer> {
    Seat findByStopStopIdAndSeatClassAndAvailableSeatsGreaterThanEqual(StopId stopId, SeatClass seatClass, Integer availableSeats);
}
