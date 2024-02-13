package com.example.airline_reservation.dtos;

import com.example.airline_reservation.entities.Flight;
import com.example.airline_reservation.entities.Passenger;
import com.example.airline_reservation.entities.Payment;
import com.example.airline_reservation.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer id;
    Integer numberOfSeatsBooked;
    String type;
    boolean paymentStatus;
    LocalDateTime dateTime;
    Integer flightId;
    Integer sourceNumber;
    Integer destinationNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer paymentId;
    Long userId;

    List<PassengerDTO> passengers;
}
