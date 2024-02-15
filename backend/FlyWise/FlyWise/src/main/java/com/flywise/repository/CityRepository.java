package com.flywise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flywise.pojos.City;

public interface CityRepository extends JpaRepository<City, Integer>{
	
}
