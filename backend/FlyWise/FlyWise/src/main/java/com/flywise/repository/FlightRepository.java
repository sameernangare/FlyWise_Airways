package com.flywise.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flywise.pojos.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("from Flight where source=:source and destination=:destination and travelDate=:travelDate")
	List<Flight> findByCondition(String source, String destination, LocalDate travelDate);
}
