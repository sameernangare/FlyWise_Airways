package com.flywise.pojos;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name="transaction_number")
	private double transactionNumber;
	
	@Column(name = "payment_status")
	private int paymentStatus;

	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;

	@Column(name = "total_payment")
	private double totalPayment;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	public Payment(double transactionNumber, int paymentStatus, LocalDate paymentDate, double totalPayment) {
		super();
		this.transactionNumber = transactionNumber;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.totalPayment = totalPayment;
	}

	
	
}