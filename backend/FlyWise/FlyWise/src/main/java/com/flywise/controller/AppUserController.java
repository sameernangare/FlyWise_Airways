package com.flywise.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.dto.AppUserDto;
import com.flywise.dto.CustomDto;
import com.flywise.exception.FlightException;
import com.flywise.exception.ResourceNotFoundException;
import com.flywise.exception.UserException;
import com.flywise.pojos.AppUser;
import com.flywise.pojos.Booking;
import com.flywise.pojos.Cancellation;
import com.flywise.pojos.City;
import com.flywise.pojos.Feedback;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Payment;
import com.flywise.repository.AppUserRepository;
import com.flywise.repository.BookingRepository;
import com.flywise.repository.CancellationRepository;
import com.flywise.repository.CitiesRepository;
import com.flywise.repository.FeedbackRepository;
import com.flywise.repository.FlightRepository;
import com.flywise.repository.PaymentRepository;
import com.flywise.service.IAppUserService;
import com.flywise.service.IFlightService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AppUserController {
	
	@Autowired
	private IAppUserService appUserService;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private IFlightService flightService;
	
	@Autowired
	private AppUserRepository userRepo;
	
	@Autowired
	private CancellationRepository cancellationRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private FeedbackRepository feedbackRepo;
	
	@Autowired
	private CitiesRepository cityRepo;
	
//--------------------------------------------------------------------------------------------------------------------------
	
	// new registration of a user
	
	@PostMapping(value = "/register", consumes = { "application/xml", "application/json" })
	public String registerUser(@RequestBody AppUserDto appUserDto) {

		appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
		
		try {
			
			appUserService.registerUser(appUserDto);
			return "User registered successfully with first name " + appUserDto.getFirstName();
			
		} catch (UserException e) {

			return "Duplicate user entry. " + e.getMessage();
		}
	}

//--------------------------------------------------------------------------------------------------------------------------
	
	// post a feedback
	
	@PostMapping(value = "/user/feedback", produces = "application/json")
	public ResponseEntity<?> postFeedback(@RequestParam("uid") int userId, @RequestParam("feedback") String feedback) {
		
		Feedback feedbackToPost = new Feedback();
		
		feedbackToPost.setFeedback(feedback);
		
		AppUser appUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with userId " + userId));
		
		feedbackToPost.setAppUser(appUser);
		
		feedbackRepo.save(feedbackToPost);
		
		return new ResponseEntity<String>("Your feedback was sent successfully", HttpStatus.OK);
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	// search flight with source, destination, travelDate
	
	@GetMapping(value = "/fetch", produces = "application/json")
	public ResponseEntity<?> searchFlightByCondition(@RequestParam("src") String source,
			@RequestParam("dest") String destination, @RequestParam("dt") String date) {
		
		try {
			
			LocalDate travelDate = LocalDate.parse(date);
			
			List<Flight> flights = flightService.fetchFlightsWithCondition(source, destination, travelDate);
			
			return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
			
		} catch (FlightException e) {
			
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//--------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/user/confirm")
	public ResponseEntity<?> confirmBooking(@RequestParam("bid") int bookingId) {
		
		CustomDto customDto = appUserService.confirmBooking(bookingId);
		
		return new ResponseEntity<CustomDto>(customDto, HttpStatus.OK);
	}

//--------------------------------------------------------------------------------------------------------------------------
	
	
	@PostMapping("/user/cancel")
	public ResponseEntity<?> cancelBooking(@RequestParam("bid") int bookingId, @RequestBody Cancellation cancellation) {

		Booking cancelBooking = bookingRepo.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Invalid booking Id"));
		
		Flight changeFlightSeats = flightService.getFlightById(cancelBooking.getFlight().getFlightId());
		
		if (cancelBooking != null) {
			
			if (cancelBooking.getBookingStatus() == 0)
				return new ResponseEntity<String>("Booking is not yet confirmed", HttpStatus.OK);
				
			else if (cancelBooking.getBookingStatus() == 1) {
				
				Payment payment = paymentRepo.findById(cancelBooking.getPayment().getPaymentId()).orElseThrow(() -> new ResourceNotFoundException("Invalid booking "));
				
				cancelBooking.setBookingStatus(2);
				
				cancelBooking.setPaymentStatus(2);// 2 for cancelled 1 for booking confirmed 0-Not confirmed
				
				cancellation.setBooking(cancelBooking);
				
				cancellation.setCancellationDate(LocalDate.now());
				
				cancellation.setRefundAmount(payment.getTotalPayment() * 0.50);
				
				payment.setPaymentStatus(2);
				
				int seats = cancelBooking.getNumberOfSeatsToBook();
				
				changeFlightSeats.setAvailableSeats(changeFlightSeats.getAvailableSeats() + seats);
				
				flightRepo.save(changeFlightSeats);
				
				paymentRepo.save(payment);
				
				cancellationRepo.save(cancellation);
				
				bookingRepo.save(cancelBooking);
				return new ResponseEntity<String>("Your booking has been cancelled", HttpStatus.OK);
			}

		}
		return new ResponseEntity<String>("Book your ticket first", HttpStatus.OK);

	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/cities")
	public List<City> getCities() {
		
		List<City> listOfCities = cityRepo.findAll();
		
		return listOfCities;
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/user/bookings")
	public ResponseEntity<?> pastBookings(@RequestParam("uid") int userId) {
		
		List<Booking> pastBooking = appUserService.pastBooking(userId);
		
		return new ResponseEntity<List<Booking>>(pastBooking, HttpStatus.OK);
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/user/getfeedback")
	public List<Feedback> getUserFeedback(@RequestParam("uid") int userId) {
		
		List<Feedback> listOfFeedback = feedbackRepo.findAll();

		return listOfFeedback.stream()
								.filter(f -> f.getAppUser().getUserId() == userId)
								.collect(Collectors.toList());
	}
	
}
