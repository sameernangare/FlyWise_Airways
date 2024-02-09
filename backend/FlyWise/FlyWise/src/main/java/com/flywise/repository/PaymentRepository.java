package com.flywise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flywise.pojos.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
