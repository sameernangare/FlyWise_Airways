package com.flywise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flywise.pojos.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
