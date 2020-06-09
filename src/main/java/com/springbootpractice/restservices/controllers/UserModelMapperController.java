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

import com.springbootpractice.restservices.dtos.UserDto;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/{id}")
	public UserDto getUserById(@PathVariable @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User doesn't exist, please provide valid id");
		}

		User user = userOptional.get();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		

		return userDto;

	}
}
