package com.flywise.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flywise.pojos.Booking;
import com.flywise.repository.BookingRepository;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService{

	@Autowired
	private BookingRepository bookingRepo;
	@Override
	public void addBooking(Booking booking) {
		bookingRepo.save(booking);
	}

}
