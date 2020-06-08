package com.springbootpractice.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.entities.Views;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/jsonview/users")
public class UserJsonViewController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/external/{id}")
	@JsonView(Views.External.class)
	public Optional<User> getUserByIdExternal(@PathVariable @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/internal/{id}")
	@JsonView(Views.Internal.class)
	public Optional<User> getUserByIdInternal(@PathVariable @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
