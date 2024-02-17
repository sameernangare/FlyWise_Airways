package com.flywise.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@NoArgsConstructor
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passenger_id")
	private int passengerId;
	
	@Column(name = "passenger_name", length = 50, nullable = false)
	private String passengerName;
	
	@Column(name = "gender", length = 10)
	private String gender;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "seat_number")
	private String seatNumber;

	//@JsonBackReference
	@JsonIgnoreProperties(value = "passengers")
	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	

	public Passenger(String passengerName, String gender, int age, String seatNumber) {
		super();
		this.passengerName = passengerName;
		this.gender = gender;
		this.age = age;
		this.seatNumber = seatNumber;
	}

	
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", gender=" + gender
				+ ", age=" + age + ", seatNumber=" + seatNumber + "]";
	}
	
}
