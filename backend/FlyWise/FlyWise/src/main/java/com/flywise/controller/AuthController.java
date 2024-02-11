package com.flywise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.dto.LoginResponse;
import com.flywise.pojos.AppUser;
import com.flywise.pojos.Credentials;
import com.flywise.repository.AppUserRepository;
import com.flywise.security.JwtUtil;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@Autowired
	private AppUserRepository appUserRepo;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Credentials cred) {
		System.out.println("Authenticating: " + cred);
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword());
			System.out.println("BEFORE: " + auth);
			auth = authManager.authenticate(auth);
			System.out.println("AFTER: " + auth);
			User user = (User)auth.getPrincipal();
			
			AppUser appUser=appUserRepo.findByEmail(cred.getEmail());
			String token = jwtUtils.generateToken(user.getUsername(),appUser.getUserId(),appUser.getRole());
			String role=appUser.getRole();
			int userId=appUser.getUserId();
			String firstName=appUser.getFirstName();
			  // Set the token in the Authorization header of the response
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.add("Authorization", "Bearer " + token);
//			Return a response with the headers
			 LoginResponse response = new LoginResponse(token,userId,role,firstName);
			    return ResponseEntity.ok(response);
	        
		
			
		}catch (BadCredentialsException e) {
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
