package com.example.airline_reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightDTO {
    Integer id;
    String name;
    byte[] logo;

    Integer numberOfPass;
    List<StopDTO> stops;
    Integer availableSeats, totalSeats, fare;
    String type;
}
