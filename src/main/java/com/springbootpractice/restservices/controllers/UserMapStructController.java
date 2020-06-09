package com.springbootpractice.restservices.controllers;

import java.util.List;
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

import com.springbootpractice.restservices.dtos.UserMapStructsDto;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.mappers.UserMapper;
import com.springbootpractice.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserMapStructsDto> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return userMapper.userListToUserDtoList(users);
	}
	
	@GetMapping(path = "/{id}")
	public UserMapStructsDto getUserById(@PathVariable @Min(1) Long id) {
		try {
			User user = userService.getUserById(id).get();
			return userMapper.userToUserDto(user);
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
