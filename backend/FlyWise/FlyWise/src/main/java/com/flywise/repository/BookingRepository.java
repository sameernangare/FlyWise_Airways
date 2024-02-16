package com.flywise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flywise.pojos.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
