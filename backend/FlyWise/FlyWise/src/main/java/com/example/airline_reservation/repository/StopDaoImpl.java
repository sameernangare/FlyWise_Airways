package com.example.airline_reservation.repository;

import com.example.airline_reservation.entities.Stop;
import com.example.airline_reservation.entities.StopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface StopDaoImpl extends JpaRepository<Stop, StopId> {
    @Query(value = "select * from stops a, stops b where a.flight_id = b.flight_id and a.stop_number < b.stop_number and a.stop_name = :from and b.stop_name=:to and DATE(a.departure_date_time) = :date and DATE(b.departure_date_time) = :date union select * from stops a, stops b where a.flight_id = b.flight_id and a.stop_number > b.stop_number and a.stop_name = :to and b.stop_name=:from and DATE(a.departure_date_time) = :date and DATE(b.departure_date_time) = :date", nativeQuery = true)
    List<Stop> getStops(String from, String to, Date date);

    @Query(value = "select s from Stop s where s.stopId.flight.flightId=:flightId and s.stopId.stopNumber >= :source and s.stopId.stopNumber <= :destination")
    List<Stop> getIntermdiateStops(int source, int destination, int flightId);

    Stop findByStopIdStopNumberAndStopIdFlightFlightId(Integer stopNumber, Integer flightId);

}
