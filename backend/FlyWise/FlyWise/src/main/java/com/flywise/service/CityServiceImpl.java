package com.flywise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flywise.pojos.City;
import com.flywise.repository.CityRepository;

@Service
@Transactional
public class CityServiceImpl implements CitiesService{

	@Autowired
	private CityRepository cityRepo;
	
	@Override
	public List<City> getAllCity() {
		// TODO Auto-generated method stub
		return cityRepo.findAll();
	}

}
