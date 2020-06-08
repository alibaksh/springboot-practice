package com.springbootpractice.restservices.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/jacksonfilter/users")
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public MappingJacksonValue getUserById(@PathVariable @Min(1) Long id) {
		try {
			Set<String> fields = new HashSet<>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");

			User user = userService.getUserById(id).get();
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapper.setFilters(filterProvider);

			return mapper;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/params/{id}")
	public MappingJacksonValue getUserById(@PathVariable @Min(1) Long id, @RequestParam Set<String> fields) {
		try {

			User user = userService.getUserById(id).get();
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapper.setFilters(filterProvider);

			return mapper;

		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
