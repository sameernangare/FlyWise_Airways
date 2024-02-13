package com.example.airline_reservation.dtos;

import com.example.airline_reservation.entities.Flight;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class StopDTO {
    String stopName;
    int stopNumber;
    LocalDateTime arrivalDateTime, departureDateTime;
    Integer stopIdFlightId;
}
