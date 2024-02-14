package com.flywise.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.dto.AppUserDto;
import com.flywise.pojos.AppUser;
import com.flywise.pojos.Feedback;
import com.flywise.pojos.Passenger;
import com.flywise.repository.FeedbackRepository;
import com.flywise.service.IAppUserService;
import com.flywise.service.IFlightService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAppUserService appUserService;
	
	@Autowired
	private IFlightService flightService;
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	@GetMapping("/getusers")
	public List<AppUserDto> getAllUsers(){
		List<AppUser> listOfUsers = appUserService.fetchAllUsers();
		
		return listOfUsers
				.stream()
				.filter(c -> c.getRole().equals("ROLE_USER"))
				.map(c -> new AppUserDto(c.getUserId(),c.getFirstName(), c.getLastName(), c.getEmail(),
						c.getPassword(), c.getPhoneNumber(), c.getGovtId(), c.getGovtIdNumber()))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/passengers")
	public ResponseEntity<?> getUsersByFlightId(@RequestParam("fid") int flightId){
		List<Passenger> listOfPassangers = flightService.getPassengersByFlightId(flightId);
		if(!listOfPassangers.isEmpty())
			return new ResponseEntity<List<Passenger>>(listOfPassangers, HttpStatus.OK);
		else
			return new ResponseEntity<String>("No Passangers found.", HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("/feedback")
	public List<Feedback> getUserFeedback(){
		List<Feedback> listOfFeedback = feedbackRepo.findAll();
		return listOfFeedback;
	}
	
	
	
	
	
	
	
	
	
}
