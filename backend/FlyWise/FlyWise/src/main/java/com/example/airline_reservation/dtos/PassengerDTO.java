package com.example.airline_reservation.dtos;

import com.example.airline_reservation.entities.Booking;
import com.example.airline_reservation.entities.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    String name;
    String gender;
    Integer age;
    Integer seatNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer bookingId;
}
