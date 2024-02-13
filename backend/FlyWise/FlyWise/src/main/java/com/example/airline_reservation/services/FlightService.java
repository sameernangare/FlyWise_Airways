package com.example.airline_reservation.services;

import com.example.airline_reservation.dtos.FlightDTO;
import com.example.airline_reservation.dtos.StopDTO;
import com.example.airline_reservation.entities.Seat;
import com.example.airline_reservation.entities.SeatClass;
import com.example.airline_reservation.entities.Stop;
import com.example.airline_reservation.repository.FlightDaoImpl;
import com.example.airline_reservation.repository.SeatDaoImpl;
import com.example.airline_reservation.repository.StopDaoImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class FlightService {
    @Autowired
    private FlightDaoImpl flightDao;
    @Autowired
    private StopDaoImpl stopDao;
    @Autowired
    private SeatDaoImpl seatDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<FlightDTO> getFlights(String from, String to, LocalDate date, String numPass, String type) {

        //get to and from stops of all flights for the given date
        Date sqlDate = Date.valueOf(date);

        List<Stop> stops = stopDao.getStops(from, to, sqlDate);

        stops = stops.stream().sorted(Comparator.comparingInt((Stop a) -> a.getStopId().getFlight().getFlightId())).collect(Collectors.toList());


        //get all the intermdiate stops
        List<List<Stop>> allFlightsStops = new ArrayList<>();

        for (int i = 0; i < stops.size() - 1; i += 2) {
            int source = stops.get(i).getStopId().getStopNumber();
            int destination = stops.get(i + 1).getStopId().getStopNumber();
            int flightId = stops.get(i).getStopId().getFlight().getFlightId();

            List<Stop> intermediateStops = stopDao.getIntermdiateStops(source, destination, flightId);

            allFlightsStops.add(intermediateStops);
        }
        //check whether the intermdiate stops have valid available seats and if valid add into flightDTO list which will be sent to the user

        List<FlightDTO> flights = new ArrayList<>();

        for (int j = 0; j < allFlightsStops.size(); j++) {
            List<Stop> tempStops = allFlightsStops.get(j);
            boolean flag = false;
            int fare = 0, availableSeats = Integer.MAX_VALUE, totalSeats = Integer.MAX_VALUE;
            for (int i = 0; i < tempStops.size(); i++) {
//                System.out.println(stop.getStopName());

                Seat seat = seatDao.findByStopStopIdAndSeatClassAndAvailableSeatsGreaterThanEqual(tempStops.get(i).getStopId(), SeatClass.valueOf(type), Integer.parseInt(numPass));

                if (seat == null) {
                    System.out.println("Seat not found");
                    flag = true;

                    break;
                } else {
                    if (i > 0) {
                        fare += seat.getFare();
                    }
                    availableSeats = Math.min(availableSeats, seat.getAvailableSeats());
                    totalSeats = Math.min(totalSeats, seat.getAvailableSeats());
                    System.out.println(seat.getSeatId());
                }
            }
            if (flag) {
                allFlightsStops.remove(tempStops);
                j--;
            } else {
                Stop stop = tempStops.get(0);

                List<StopDTO> stopDTOs = tempStops.stream().map(e -> modelMapper.map(e, StopDTO.class)).collect(Collectors.toList());

                FlightDTO flightDTO = new FlightDTO(stop.getStopId().getFlight().getFlightId(), stop.getStopId().getFlight().getFlightName(), stop.getStopId().getFlight().getLogo(), Integer.parseInt(numPass),
                        stopDTOs, availableSeats, totalSeats, fare, type);

                flights.add(flightDTO);
            }
        }

        return flights;
    }
}
