package com.flywise.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {

	private int paymentId;

	private long transactionNumber;
	
	private int paymentStatus;
	
	private LocalDate paymentDate;
	
	private double totalPayment;
	
	private AppUserDto appUserDto;
	
	private BookingDto bookingDto;
}
