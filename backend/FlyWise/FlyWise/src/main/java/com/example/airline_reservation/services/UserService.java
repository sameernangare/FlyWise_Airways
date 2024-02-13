package com.example.airline_reservation.services;


import com.example.airline_reservation.dtos.Signup;

public interface UserService {
    //sign up
    Signup userRegistration(Signup reqDTO);
}
