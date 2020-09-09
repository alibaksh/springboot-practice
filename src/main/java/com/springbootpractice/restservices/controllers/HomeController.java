package com.springbootpractice.restservices.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Value("${server.port}")
	String inusedPort;

	@GetMapping(path = "/health")
	public String healthCheck() {

		return "The service is up and running on Port: " + inusedPort;
	}
}
