package com.flywise.pojos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	private int flightId;
	
	@Column(name="source", nullable = false, length = 50)
	private String source;
	
	@Column(name="destination", nullable = false, length = 50)
	private String destination;
	
	@Column(name="travel_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate travelDate;
	
	@Column(name = "departure_time", nullable = false)
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime departureTime;
	
	@Column(name = "arrival_time", nullable = false)
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime arrivalTime;
	
	@Column(name = "economy_fare", nullable = false)
	private double economyFare;
	
	@Column(name = "business_fare", nullable = false)
	private double businessFare;
	
	@Column(name = "firstclass_fare", nullable = false)
	private double firstClassFare;
	
	@Column(name = "available_seats", nullable = false)
	private int availableSeats;

	@JsonIgnore
	@OneToMany(mappedBy = "flight",cascade=CascadeType.ALL)
	private List<Passenger> listOfPassengers=new ArrayList<>();
	
	
	public Flight(String source, String destination, LocalDate travelDate, LocalTime departureTime,
			LocalTime arrivalTime, double economyFare, double businessFare, double firstClassFare, int availableSeats) {
		super();
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.economyFare = economyFare;
		this.businessFare = businessFare;
		this.firstClassFare = firstClassFare;
		this.availableSeats = availableSeats;
	}
	
}
