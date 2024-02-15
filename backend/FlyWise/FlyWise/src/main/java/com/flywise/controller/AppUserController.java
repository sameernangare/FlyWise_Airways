package com.flywise.controller;

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
import com.flywise.exception.UserException;
import com.flywise.service.IAppUserService;

@RestController
@CrossOrigin
public class AppUserController {
	
	@Autowired
	private IAppUserService appUserService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// new registration of a user
	@PostMapping(value = "/register", consumes = { "application/xml", "application/json" })
	public String registerUser(@RequestBody AppUserDto appUserDto) {
		appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
		
		try {
			appUserService.registerUser(appUserDto);
			return "User registered successfully with first name " + appUserDto.getFirstName();
		}
		catch(UserException e){
			return "Duplicate user entry. " + e.getMessage();
		}
		
	}
	
	@GetMapping("/user/confirm")
	public ResponseEntity<?> confirmBooking(@RequestParam("bid") int bookingId) {
		CustomDto customDto = appUserService.confirmBooking(bookingId);
		return new ResponseEntity<CustomDto>(customDto, HttpStatus.OK);
	}
}
