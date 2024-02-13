package com.example.airline_reservation.dtos;

import com.example.airline_reservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoImpl extends JpaRepository<User, Long> {

}
