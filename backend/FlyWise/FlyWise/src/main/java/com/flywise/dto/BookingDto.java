package com.flywise.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDto {

	private int bookingId;
	private int numberOfSeatsToBook;
	private int bookingStatus;
	private int paymentStatus;
	private LocalDate bookingDate;
	
	
	private AppUserDto appUserDto;
	
	private FlightDto flightDto;
	private int classId;
	private List<PassengerDto> listOfPassengers; 
	public BookingDto(int numberOfSeatsToBook, int classId) {
		super();
		this.numberOfSeatsToBook = numberOfSeatsToBook;
		this.classId = classId;
	}
	
	
}
