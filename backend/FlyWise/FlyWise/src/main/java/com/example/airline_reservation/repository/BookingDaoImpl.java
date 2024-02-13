package com.example.airline_reservation.repository;

import com.example.airline_reservation.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDaoImpl extends JpaRepository<Booking, Long> {

}
