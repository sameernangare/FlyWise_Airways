package com.flywise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.dto.FlightDto;
import com.flywise.exception.FlightException;
import com.flywise.service.IFlightService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private IFlightService flightService;
	
	// Adding Flight
	@PostMapping(value = "/new", consumes = { "application/xml", "application/json" })
	public String addFlight(@RequestBody FlightDto flightDto) {
		try {
			flightService.addFlight(flightDto);
		}catch (FlightException e) {
			return ""+e.getMessage();
		}
		return "Flight Added Successfully";
	}
	
	// remove a flight
		@DeleteMapping(value = "/remove")
		public ResponseEntity<?> removeFlight(@RequestParam("fid") int flightId) {
			flightService.removeFlight(flightId);
			
			
			return new ResponseEntity<String>("Requested flight with id " + flightId + " has been removed.",HttpStatus.OK);
			
		}
		
		
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
}
