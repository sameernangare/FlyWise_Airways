package com.flywise.dto;

import java.time.LocalDate;
import java.time.LocalTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

	private int flightId;
	
	private String source;
	private String destination;
	
	private LocalDate travelDate;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private double economyFare;
	private double firstClassFare;
	private double businessFare;
	private int availableSeats;
	public FlightDto(String source, String destination, LocalDate travelDate) {
		super();
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
	}
	
	
	
}
