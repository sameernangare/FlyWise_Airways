package com.example.airline_reservation.controllers;

import com.example.airline_reservation.dtos.BookingDTO;
import com.example.airline_reservation.exceptions.ResourceNotFoundException;
import com.example.airline_reservation.http.*;
import com.example.airline_reservation.entities.Booking;
import com.example.airline_reservation.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingsController {
    @Autowired
    BookingService bookingService;

    @PostMapping
    public Response bookFlight(@RequestBody BookingDTO bookingDto) {
        return bookingService.bookFlight(bookingDto);
    }

//    @ExceptionHandler(value
//            = ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResourceNotFoundException
//    handleCustomerAlreadyExistsException(
//            ResourceNotFoundException ex)
//    {
//        return new ResourceNotFoundException(HttpStatus.NO_CONTENT.value(),
//                ex.getMessage());
//    }
}
