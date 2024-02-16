package com.flywise.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cancellation")
@Getter
@Setter
@NoArgsConstructor
public class Cancellation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cancellationId;
	
	@Column(name = "refund_amount")
	private double refundAmount;
	
	@Column(name = "cancel_reason", length = 500)
	private String reason;
	
	@Column(name = "cancellation_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cancellationDate;
	
	//unidirectional
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	
	public Cancellation(double refundAmount, String reason, LocalDate cancellationDate) {
		super();
		this.refundAmount = refundAmount;
		this.reason = reason;
		this.cancellationDate = cancellationDate;
	}

	
	@Override
	public String toString() {
		return "Cancellation [cancellationId=" + cancellationId + ", refundAmount=" + refundAmount + ", reason="
				+ reason + ", cancellationDate=" + cancellationDate + "]";
	}
	
}
