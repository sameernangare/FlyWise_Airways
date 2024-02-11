
package com.flywise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

	private int userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	//skips the field/property during serialization
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String phoneNumber;

	private String govtId;
	
	private String govtIdNumber;
	
	public AppUserDto(String firstName, String lastName, String email, String password, String phoneNumber,
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
}
