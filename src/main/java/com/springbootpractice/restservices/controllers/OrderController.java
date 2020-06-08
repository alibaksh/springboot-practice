package com.springbootpractice.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpractice.restservices.entities.Order;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.NotFoundException;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.repositories.UserRepository;
import com.springbootpractice.restservices.services.OrderService;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderService orderService;

	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrdersByUserId(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User doesn't exist");
		}

		return user.get().getOrders();
	}

	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User doesn't exist");
		}
		order.setUser(user.get());
		return orderService.createOrder(order);
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderById(@PathVariable Long orderId) throws NotFoundException {
		
		Optional<Order> order = orderService.getOrderById(orderId);
		if (!order.isPresent()) {
			throw new NotFoundException("Order");
		}
		
		return order.get();
	}
}
