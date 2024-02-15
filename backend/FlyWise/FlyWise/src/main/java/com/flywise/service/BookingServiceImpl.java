package com.flywise.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flywise.exception.ResourceNotFoundException;
import com.flywise.pojos.Booking;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Passenger;
import com.flywise.repository.BookingRepository;
import com.flywise.repository.PassengerRepository;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService{

	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Override
	public void addBooking(Booking booking) {
		bookingRepo.save(booking);
	}
	@Override
	public String addPassengers(List<Passenger> list, int bid) {
		Booking booking = bookingRepo.findById(bid).orElseThrow(()->new ResourceNotFoundException("Invalid Booking Id"));
		Flight flight = booking.getFlight();
		booking.setPassengers(list);
		bookingRepo.save(booking);
		
		// now add passengers in passengers table
		for(int i=0; i < booking.getNumberOfSeatsToBook(); i++)
		{
			list.get(i).setBooking(booking);
			list.get(i).setFlight(flight);
			
			passengerRepo.save(list.get(i));
		}
		return "Passengers added successfully";
	}

}
