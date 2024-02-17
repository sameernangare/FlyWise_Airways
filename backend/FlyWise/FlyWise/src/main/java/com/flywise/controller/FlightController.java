package com.flywise.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.dto.FlightDto;
import com.flywise.exception.FlightException;
import com.flywise.pojos.Flight;
import com.flywise.service.IFlightService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private IFlightService flightService;

//--------------------------------------------------------------------------------------------------------------------------
	
	// adding new flight
	
	@PostMapping(value = "/new", consumes = { "application/xml", "application/json" })
	public String addFlight(@RequestBody FlightDto flightDto) {

		try {
			
			flightService.addFlight(flightDto);
			
		} catch (FlightException e) {
			
			return "" + e.getMessage();
		}
		
		return "Flight added successfully";
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	// display all flights
	
	@GetMapping(value = "/all", produces = "application/json")
	public List<FlightDto> showAllFlights() {
		
		List<Flight> flights = flightService.fetchAllFlights();
		
		return flights.stream()
						.map(c -> new FlightDto(c.getFlightId(), c.getSource(), c.getDestination(), c.getTravelDate(),
								c.getArrivalTime(), c.getDepartureTime(), c.getEconomyFare(), c.getFirstClassFare(),
								c.getBusinessFare(), c.getAvailableSeats()))
						.collect(Collectors.toList());
	}

//--------------------------------------------------------------------------------------------------------------------------
	
	// remove a flight
	
	@DeleteMapping(value = "/remove")
	public ResponseEntity<?> removeFlight(@RequestParam("fid") int flightId) {
		
		flightService.removeFlight(flightId);
		
		return new ResponseEntity<String>("Requested flight with id " + flightId + " has been removed.",HttpStatus.OK);
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	// update a flight
	
	@PutMapping(value = "/update", produces = "application/json")
	public String updateFlight(@RequestBody FlightDto flightDto) {
		
		try {
			
			flightService.updateFlight(flightDto);
			
			return "Requested flight has been updated.";
			
		} catch (FlightException e) {
			
			return "" + e.getMessage();
		}
	}

//--------------------------------------------------------------------------------------------------------------------------
	
	// search a flight by flightId
	
	@GetMapping(value = "/get", produces = "application/json")
	public FlightDto searchFlight(@RequestParam("fid") int flightId) {
		
		Flight getFlight = flightService.getFlightById(flightId);
		
		return new FlightDto(getFlight.getFlightId(), getFlight.getSource(), getFlight.getDestination(),
				getFlight.getTravelDate(), getFlight.getArrivalTime(), getFlight.getDepartureTime(),
				getFlight.getEconomyFare(), getFlight.getFirstClassFare(), getFlight.getBusinessFare(),
				getFlight.getAvailableSeats());
	}

//--------------------------------------------------------------------------------------------------------------------------
	
	// search flight with source, destination, travelDate
	
	@GetMapping(value = "/fetch", produces = "application/json")
	public ResponseEntity<?> searchFlightByCondition(@RequestParam("src") String source,
													@RequestParam("dest") String destination,
													@RequestParam("dt") String date) {
		
		try {
			
			LocalDate travelDate = LocalDate.parse(date);
			
			List<Flight> correctFlights=new ArrayList<>();
			
			if(travelDate.isAfter(LocalDate.now()) || travelDate.isEqual(LocalDate.now())) {
				
				List<Flight> flights = flightService.fetchFlightsWithCondition(source, destination, travelDate);
				
				for(int i= 0;i<flights.size();i++) {
					
					if(travelDate.isAfter(LocalDate.now()) || LocalTime.now().isBefore(flights.get(i).getDepartureTime())) {
						correctFlights.add(flights.get(i));
					}	
				}
				
				return new ResponseEntity<List<Flight>>(correctFlights, HttpStatus.OK);
				
			} else 
				
				return new ResponseEntity<String>("Cannot pick older date",HttpStatus.BAD_REQUEST);
			
		} catch (FlightException e) {
			
			return new ResponseEntity<String>("Flight not found.", HttpStatus.NOT_FOUND);
		}
	}
}
