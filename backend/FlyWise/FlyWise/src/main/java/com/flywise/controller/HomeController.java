package com.flywise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flywise.pojos.City;
import com.flywise.service.CitiesService;

@RestController
public class HomeController {
	
	@Autowired
	private CitiesService cityService;
	
	@RequestMapping("/")
	public String show() {
		return "<h1>FlyWise Airways</h1>";
	}
	
	@RequestMapping("/cities")
	public List<City> getAllCities()
	{
		return cityService.getAllCity();
	}
}
