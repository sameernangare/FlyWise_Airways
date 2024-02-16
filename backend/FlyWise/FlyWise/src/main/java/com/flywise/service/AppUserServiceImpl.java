package com.flywise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flywise.dto.AppUserDto;
import com.flywise.dto.CustomDto;
import com.flywise.dto.FlightDto;
import com.flywise.dto.PassengerDto;
import com.flywise.exception.ResourceNotFoundException;
import com.flywise.exception.UserException;
import com.flywise.pojos.AppUser;
import com.flywise.pojos.Booking;
import com.flywise.pojos.Flight;
import com.flywise.pojos.Passenger;
import com.flywise.repository.AppUserRepository;
import com.flywise.repository.BookingRepository;

@Service
@Transactional
public class AppUserServiceImpl implements IAppUserService {
	
	@Autowired
	private AppUserRepository userRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public List<AppUser> fetchAllUsers() {
		
		List<AppUser> appUsers = userRepo.findAll();
		return appUsers;
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public void registerUser(AppUserDto appUserDto) throws UserException {
		
		// Check if user already exists
		
		List<AppUser> appUsers = fetchAllUsers();

		AppUser tempUser = null;
		
		for (AppUser u : appUsers) {
			if (u.getEmail().equals(appUserDto.getEmail()))
				tempUser = u;
		}
		
		if (tempUser == null) {

			userRepo.save(new AppUser(appUserDto.getFirstName(), appUserDto.getLastName(), appUserDto.getEmail(),
					appUserDto.getPassword(), appUserDto.getPhoneNumber(), appUserDto.getGovtId(),
					appUserDto.getGovtIdNumber(), "ROLE_USER"));

		} else {
			
			throw new UserException("User already exists with username " + tempUser.getEmail());
		}
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public CustomDto confirmBooking(int bid) {
		
		Booking booking = bookingRepo.findById(bid).orElseThrow(() -> new ResourceNotFoundException("Invalid booking Id"));
		
		Flight flight = booking.getFlight();
		
		List<Passenger> list = flight.getListOfPassengers();
		
		List<PassengerDto> listOfPassengerDto=new ArrayList<PassengerDto>();
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getBooking().getBookingId() == booking.getBookingId()) {
				listOfPassengerDto.add(new PassengerDto(list.get(i).getPassengerId(), list.get(i).getPassengerName(),
						list.get(i).getGender(), list.get(i).getAge(), list.get(i).getSeatNumber(),
						list.get(i).getBooking().getBookingId()));
			}

		}
			
		double payment = 0.0;
		
		if(booking.getClasses().getClassId() == 1)
			payment = booking.getNumberOfSeatsToBook() * flight.getBusinessFare();
		
		else if(booking.getClasses().getClassId() == 2)
			payment = booking.getNumberOfSeatsToBook() * flight.getFirstClassFare();
		
		else if(booking.getClasses().getClassId() == 3)
			payment = booking.getNumberOfSeatsToBook() * flight.getEconomyFare();
		
		
		CustomDto customDto = new CustomDto(
				new FlightDto(flight.getFlightId(), flight.getSource(), flight.getDestination(), flight.getTravelDate(),
						flight.getArrivalTime(), flight.getDepartureTime(), flight.getEconomyFare(),
						flight.getFirstClassFare(), flight.getBusinessFare(), flight.getAvailableSeats()),
				
				listOfPassengerDto,
				
				payment);
		
		return customDto;
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public List<Booking> pastBooking(int uid) {
		
		AppUser user = userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("No such user available"));
		List<Booking> bookingList = user.getBookingList();
		
		return bookingList;
		
//		return bookingList.stream()
//							.filter(c -> c.getBookingStatus() == 1)
//							.collect(Collectors.toList());
	}
	
}
