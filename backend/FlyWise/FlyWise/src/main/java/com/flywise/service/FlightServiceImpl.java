package com.flywise.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flywise.dto.FlightDto;
import com.flywise.exception.FlightException;
import com.flywise.exception.ResourceNotFoundException;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Passenger;
import com.flywise.repository.FlightRepository;
import com.flywise.repository.PassengerRepository;



@Service
@Transactional
public class FlightServiceImpl implements IFlightService {
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private IAppUserService appUserService;
	@Autowired
	private PassengerRepository passengerRepo;
	@Override
	public void addFlight(FlightDto flightDto)throws FlightException {
		List<Flight> flights = fetchAllFlights();
		Flight tempFlight = null;
		for (Flight f : flights) {
			if (f.getSource().equals(flightDto.getSource()) && f.getDestination().equals(flightDto.getDestination())
					&& f.getTravelDate().equals(flightDto.getTravelDate())
					&& f.getArrivalTime().equals(flightDto.getArrivalTime())
					&& f.getDepartureTime().equals(flightDto.getDepartureTime())) {
				tempFlight = f;
			}
		}
		if (tempFlight == null) {
			flightRepo.save(new Flight(flightDto.getSource(), flightDto.getDestination(), flightDto.getTravelDate(),
					flightDto.getDepartureTime(), flightDto.getArrivalTime(), flightDto.getEconomyFare(),
					flightDto.getBusinessFare(), flightDto.getFirstClassFare(), flightDto.getAvailableSeats()));
		} else {
			throw new FlightException("Sorry, Flight already exists.");
		}
	}

	@Override
	public List<Flight> fetchAllFlights() {
		List<Flight> flights = flightRepo.findAll();
		
			
		return flights;
	}

	@Override
	public Flight getFlightById(int flightId) {
		Flight flight = flightRepo.findById(flightId).orElseThrow(()->new ResourceNotFoundException("No such flight available"));
		return flight;
	}

	@Override
	public void removeFlight(int flightId) {
		Flight flight = flightRepo.findById(flightId).orElseThrow(()->new ResourceNotFoundException("No such flight available"));
		if(flight!=null)
		flightRepo.deleteById(flightId);
		
	}

	@Override
	public void updateFlight(FlightDto flightDto) throws FlightException {
		Flight flightToUpdate = getFlightById(flightDto.getFlightId());
		if (flightToUpdate!=null) {
		
			flightToUpdate.setArrivalTime(flightDto.getArrivalTime());
			flightToUpdate.setDepartureTime(flightDto.getDepartureTime());
			flightToUpdate.setAvailableSeats(flightDto.getAvailableSeats());
			flightToUpdate.setTravelDate(flightDto.getTravelDate());
			flightToUpdate.setEconomyFare(flightDto.getEconomyFare());
			flightToUpdate.setBusinessFare(flightDto.getBusinessFare());
			flightToUpdate.setFirstClassFare(flightDto.getFirstClassFare());
			flightRepo.save(flightToUpdate);
		} else {
			throw new FlightException("Requested flight with id " + flightDto.getFlightId() + " doesn't exists..!!");
		}
	}


	
	@Override
	public List<Flight> fetchFlightsWithCondition(String source, String destination, LocalDate travelDate)
			throws FlightException {
		List<Flight> flights = flightRepo.findByCondition(source, destination, travelDate);
		if (!flights.isEmpty()) {
			return flights;
		} else {
			throw new FlightException("Flights not available for requested route.");
		}
	}

	@Override
	public List<Passenger> getPassengersByFlightId(int flightId) {
		Flight flight = flightRepo.findById(flightId).orElseThrow(()->new ResourceNotFoundException("No such flight available"));
		List<Passenger> listOfUsers = passengerRepo.findAll();
	
	return	listOfUsers.stream().filter(c->(c.getFlight().getFlightId()==flightId) && c.getBooking().getBookingStatus()==1)
			.collect(Collectors.toList());
		
	}

	
	
}
