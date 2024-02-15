package com.flywise.service;

import java.time.LocalDate;
import java.util.List;

import com.flywise.dto.FlightDto;
import com.flywise.exception.FlightException;
import com.flywise.pojos.City;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Passenger;


public interface IFlightService {
	
	void addFlight(FlightDto flightDto)throws FlightException;
	
	List<Flight> fetchAllFlights();
	
	void removeFlight(int flightId);
	
	Flight getFlightById(int flightId);
	
	void updateFlight(FlightDto flightDto) throws FlightException;
	
	List<Flight> fetchFlightsWithCondition(String source, String destination, LocalDate travelDate) throws FlightException;
	
	List<Passenger> getPassengersByFlightId(int flightId);
	
}
