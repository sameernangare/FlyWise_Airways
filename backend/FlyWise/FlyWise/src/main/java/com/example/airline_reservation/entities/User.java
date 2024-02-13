package com.example.airline_reservation.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String govtId;
    private String idNumber;
    private boolean isAdmin = false;
}
