package com.flywise.dto;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

	private int passengerId;
	
	private String passengerName;
	
	private String gender;
	private int age;
	 
	private String seatNumber;
	
	private int bookingId;

	public PassengerDto(String passengerName, String gender, int age) {
		super();
		this.passengerName = passengerName;
		this.gender = gender;
		this.age = age;
	}
	 
	
}
