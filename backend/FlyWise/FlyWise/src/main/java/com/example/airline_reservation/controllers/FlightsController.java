package com.example.airline_reservation.controllers;

import com.example.airline_reservation.dtos.FlightDTO;
import com.example.airline_reservation.dtos.StopDTO;
import com.example.airline_reservation.entities.Flight;
import com.example.airline_reservation.entities.Stop;
import com.example.airline_reservation.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {
    @Autowired
    FlightService flService;

    @GetMapping
    List<FlightDTO> getFlights(@RequestParam String from, @RequestParam String to, @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("date") LocalDate date, @RequestParam("num_pass") String numPass, @RequestParam String type) {
        return flService.getFlights(from, to, date, numPass, type);
    }
}
