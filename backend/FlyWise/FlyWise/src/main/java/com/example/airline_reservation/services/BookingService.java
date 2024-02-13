package com.example.airline_reservation.services;

import com.example.airline_reservation.dtos.BookingDTO;
import com.example.airline_reservation.dtos.UserDaoImpl;
import com.example.airline_reservation.entities.*;
import com.example.airline_reservation.exceptions.ResourceNotFoundException;
import com.example.airline_reservation.http.Response;
import com.example.airline_reservation.repository.BookingDaoImpl;
import com.example.airline_reservation.repository.FlightDaoImpl;
import com.example.airline_reservation.repository.StopDaoImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Book;

@Service
@Transactional
public class BookingService {

    @Autowired
    FlightDaoImpl flightDao;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    BookingDaoImpl bookingDao;

    @Autowired
    StopDaoImpl stopDao;

    public Response bookFlight(BookingDTO bookingDto) {
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        User user = userDao.getReferenceById(bookingDto.getUserId());
        Flight flight = flightDao.getReferenceById(bookingDto.getFlightId());

        Stop source = stopDao.getReferenceById(new StopId(bookingDto.getSourceNumber(), flight));

        Stop destination = stopDao.getReferenceById(new StopId(bookingDto.getDestinationNumber(), flight));

        booking.setUser(user);
        booking.setFlight(flight);
        booking.setSource(source);
        booking.setDestination(destination);

        bookingDao.save(booking);

        Response response = new Response("1", "Added");
//            if(!bodyBooking.equals(null)) {
//                response = new Response(bodyBooking.getId().toString(), "Booking Done");
//            }
//            else{
//                response = new Response("-1", "Booking Failed");
//            }

        return response;
    }
}
