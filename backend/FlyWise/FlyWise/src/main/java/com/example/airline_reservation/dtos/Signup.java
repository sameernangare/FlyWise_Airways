package com.example.airline_reservation.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Signup {
    @JsonProperty(access = Access.READ_ONLY) // this property only used during ser.
    private Long id;
    @NotBlank(message = "First Name required")
    private String firstName;
    private String lastName;
    @Email(message = "Invalid Email!!!")
    private String email;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private String mobileNumber;
    private String govtId;
    private String idNumber;
}