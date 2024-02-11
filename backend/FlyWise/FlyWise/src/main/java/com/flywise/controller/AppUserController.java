package com.flywise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.dto.AppUserDto;
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
}
