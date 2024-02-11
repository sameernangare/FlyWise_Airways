package com.flywise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flywise.pojos.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

}
