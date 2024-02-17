package com.flywise.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomDto {

	private AppUserDto appUserDto;
	
	private BookingDto bookingDto;
	
	private FlightDto flightDto;
	
	private List<PassengerDto> listOfPassengerDto;
	
	private double totalPayment;
	
	public CustomDto(FlightDto flightDto, List<PassengerDto> listOfPassengerDto, double totalPayment) {
		super();
		this.flightDto = flightDto;
		this.listOfPassengerDto = listOfPassengerDto;
		this.totalPayment = totalPayment;
	} 
	
}
