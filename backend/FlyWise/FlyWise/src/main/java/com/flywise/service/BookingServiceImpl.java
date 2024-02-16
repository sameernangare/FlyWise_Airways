package com.flywise.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flywise.exception.ResourceNotFoundException;
import com.flywise.pojos.Booking;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Passenger;
import com.flywise.pojos.Payment;
import com.flywise.repository.BookingRepository;
import com.flywise.repository.FlightRepository;
import com.flywise.repository.PassengerRepository;
import com.flywise.repository.PaymentRepository;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private IFlightService flightService;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public Flight getFlightDetails(int fid) {
		
		Flight flight = flightRepository.findById(fid).get();
		
		if(flight.getAvailableSeats() != 0)
			return flight;
		else
			return null;
	}

//--------------------------------------------------------------------------------------------------------------------------

	@Override
	public void addBooking(Booking booking) {
		
		bookingRepo.save(booking);
	}

//--------------------------------------------------------------------------------------------------------------------------

	@Override
	public Booking getBookingById(int bid) {
		
		Booking booking = bookingRepo.findById(bid).get();
		return booking;
	}

//--------------------------------------------------------------------------------------------------------------------------

	@Override
	public String processPayment(int bid) {
		
		Booking booking= bookingRepo.findById(bid).orElseThrow(() -> new ResourceNotFoundException("Invalid Booking id "));
		
		Flight selectedFlight = flightService.getFlightById(booking.getFlight().getFlightId());
		
		double payment = 0.0;
		
		if(booking.getClasses().getClassId() == 1)
			payment = booking.getNumberOfSeatsToBook() * selectedFlight.getBusinessFare();
		
		else if(booking.getClasses().getClassId() == 2)
			payment = booking.getNumberOfSeatsToBook() * selectedFlight.getFirstClassFare();
		
		else if(booking.getClasses().getClassId() == 3)
			payment = booking.getNumberOfSeatsToBook() * selectedFlight.getEconomyFare();
		
		
		Payment pay = new Payment(Math.random()*1000, 1, LocalDate.now(), payment);
		
		pay.setBooking(booking);
		
		pay.setAppUser(booking.getAppUser());
		
		paymentRepo.save(pay);
		
		booking.setBookingStatus(1);
		
		booking.setPaymentStatus(1);
		
		booking.setPayment(pay);
		
		bookingRepo.save(booking);
		
		return "Booking confirmed, your payment is successful.";
	}

//--------------------------------------------------------------------------------------------------------------------------

	@Override
	public String addPassengers(List<Passenger> passengersList, int bid) {
		
		Booking booking = bookingRepo.findById(bid).orElseThrow(()->new ResourceNotFoundException("Invalid booking id"));
		
		Flight flight = booking.getFlight();
		
		booking.setPassengers(passengersList);
		
		bookingRepo.save(booking);

		// add passengers in passenger table
		for (int i = 0; i < booking.getNumberOfSeatsToBook(); i++) {
			
			passengersList.get(i).setBooking(booking);
			
			passengersList.get(i).setFlight(flight);
			
			passengerRepo.save(passengersList.get(i));
		}
		
		return "Passengers added successfully.";
	}
	
//--------------------------------------------------------------------------------------------------------------------------

	@Override
	public List<Booking> getBookingsByFlightId(int flightId){
		
		List<Booking> list = bookingRepo.findAll();
		
		return list.stream()
					.filter(b -> b.getFlight().getFlightId() == flightId)
					.collect(Collectors.toList());
		
	}
	
	
}
