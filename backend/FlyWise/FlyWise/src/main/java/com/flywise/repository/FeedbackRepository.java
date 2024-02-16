package com.flywise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flywise.pojos.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
