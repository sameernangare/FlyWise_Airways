package com.flywise.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "first_name", length = 25)
	private String firstName;
	
	@Column(name = "last_name", length = 25)
	private String lastName;
	
	@Column(name = "email", length = 25, unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "phone")
	private String phoneNumber;
	
	@Column(name = "govt_id", length = 50)
	private String govtId;
	
	@Column(name = "govt_id_number", length = 50)
	private String govtIdNumber;
	
	@Column(name = "role", length = 25)
	private String role; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<Booking> bookingList = new ArrayList<Booking>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<Payment> paymentList = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "appUser")
	private List<Feedback> feedbackList= new ArrayList<>();
	
	
	public AppUser(String firstName, String lastName, String email, String password, String phoneNumber,
			String govtId, String govtIdNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.govtId = govtId;
		this.govtIdNumber = govtIdNumber;
	}
	
	
	public User toUser() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		User user = new User(email, password, 
				Collections.singletonList(authority));
		return user;
	}
	
	
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
