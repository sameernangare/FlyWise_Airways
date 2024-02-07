package com.flywise.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private String govtId;
	private String govtIdNumber;
	private String role;
	
	
	// List of Booking
	// List of Payment
	// List of FeedBack
	
	public AppUser(String firstName, String lastName, String email, String password, String phoneNumber, String govtId,
			String govtIdNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.govtId = govtId;
		this.govtIdNumber = govtIdNumber;
	}
	
	// Spring security
	
	@Override
	public String toString() {
		return "AppUser [userId=" + userId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", govtId=" + govtId
				+ ", govtIdNumber=" + govtIdNumber + "]";
	}
	
	public AppUser(String firstName, String lastName, String email, String password, String phoneNumber, String govtId,
			String govtIdNumber, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.govtId = govtId;
		this.govtIdNumber = govtIdNumber;
		this.role = role;
	}
}
