package com.flywise.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;

	@Column(name = "seats")
	private int numberOfSeatsToBook;
	
	@Column(name = "booking_status")
	private int bookingStatus;
	
	@Column(name = "payment_status")
	private int paymentStatus;
	
	@Column(name = "booking_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser appUser;
	
	@OneToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	//unidirectional
//	@OneToOne
//	@JoinColumn(name = "class_id")
//	private Classes classes;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@JsonIgnore
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<Passenger> passengers = new ArrayList<Passenger>();

	public Booking(int numberOfBookedSeats, int bookingStatus, int paymentStatus, LocalDate bookingDate) {
		super();
		this.numberOfSeatsToBook = numberOfBookedSeats;
		this.bookingStatus = bookingStatus;
		this.paymentStatus = paymentStatus;
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", numberOfBookedSeats=" + numberOfSeatsToBook + ", bookingStatus="
				+ bookingStatus + ", paymentStatus=" + paymentStatus + ", bookingDate=" + bookingDate + "]";
	}
	
}
