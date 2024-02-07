package com.flywise.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class Payment {
	private int paymentId;
	private double transactionNumber;
	private int paymentStatus;
	private LocalDate paymentDate;
	private double totalPayment;
	
	// booking
	// appUser
	
	public Payment(double transactionNumber, int paymentStatus, LocalDate paymentDate, double totalPayment) {
		super();
		this.transactionNumber = transactionNumber;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.totalPayment = totalPayment;
	}
}
