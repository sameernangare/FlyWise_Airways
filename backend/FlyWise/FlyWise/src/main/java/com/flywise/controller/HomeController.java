package com.flywise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
	public String show() {
		return "<h1>Flywise Airways</h1>";
	}
}
