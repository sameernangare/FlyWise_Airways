package com.flywise.service;

import java.util.List;

import com.flywise.pojos.Booking;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Passenger;

public interface IBookingService {

	Flight getFlightDetails(int fid);

	void addBooking(Booking booking);

	Booking getBookingById(int bid);
	
	public String processPayment(int bid);
	
	public String addPassengers(List<Passenger> list,int bid);
	
	List<Booking> getBookingsByFlightId(int flightId);
}
