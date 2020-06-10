package com.springbootpractice.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpractice.restservices.dtos.UserDtoV1;
import com.springbootpractice.restservices.dtos.UserDtoV2;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/versioning/param/users")
public class UserRequestParamVersionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/{id}", params = "version=1")
	public UserDtoV1 getUserByIdV1(@PathVariable @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User doesn't exist, please provide valid id");
		}

		User user = userOptional.get();
		UserDtoV1 userDto = modelMapper.map(user, UserDtoV1.class);

		return userDto;

	}

	@GetMapping(path = "/{id}", params = "version=2")
	public UserDtoV2 getUserByIdV2(@PathVariable @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User doesn't exist, please provide valid id");
		}

		User user = userOptional.get();
		UserDtoV2 userDto = modelMapper.map(user, UserDtoV2.class);

		return userDto;

	}
}
