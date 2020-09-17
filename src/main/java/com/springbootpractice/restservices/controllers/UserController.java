package com.springbootpractice.restservices.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.AlreadyExistsException;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.exceptions.UsernameNotFoundException;
import com.springbootpractice.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@RestController
@Validated
@RequestMapping("/users")
@Api(tags = "User management RESTful services", value = "UserController", description = "Controller used for User management")
public class UserController {

	Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retreive List of users")
	public List<User> getAllUsers() {
		logger.info("Fetching all users...", "Log4J");
		logger.error("Fetching all users...", "Log4J");
		return userService.getAllUsers();
	}

	@PostMapping
	@ApiOperation(value = "Create new user")
	public ResponseEntity<Void> createUser(
			@ApiParam("User info to be used for creating a user") @Valid @RequestBody User user,
			UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		} catch (AlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/{id}")
	public User getUserById(@PathVariable @Min(1) Long id) {
		try {
			return userService.getUserById(id).get();
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping(path = "{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping(path = "{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping(path = "/username")
	public User getUserByUsername(@RequestParam String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User doesn't exist, please provide valid username");
		}
		return user;
	}
}
