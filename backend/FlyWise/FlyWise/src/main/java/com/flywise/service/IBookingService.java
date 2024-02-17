package com.flywise.service;

import java.util.List;

import com.flywise.pojos.Booking;
import com.flywise.pojos.Passenger;
import com.flywise.pojos.Flight;

public interface IBookingService {
	void addBooking(Booking booking);
	
	String addPassengers(List<Passenger> list, int bid);
	Flight getFlightDetails(int fid);
}
