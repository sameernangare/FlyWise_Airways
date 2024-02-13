package com.example.airline_reservation.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double transactionNumber;
    LocalDateTime date;
    float totalAmount;
}
