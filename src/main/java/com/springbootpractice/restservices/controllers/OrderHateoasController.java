package com.springbootpractice.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpractice.restservices.entities.Order;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{userId}/orders")
	public CollectionModel<Order> getAllOrdersByUserId(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User doesn't exist");
		}

		List<Order> orders = user.get().getOrders();
		CollectionModel<Order> finalEntities = CollectionModel.of(orders);

		return finalEntities;
	}
}
