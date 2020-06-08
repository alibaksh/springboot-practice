package com.springbootpractice.restservices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootpractice.restservices.entities.Order;
import com.springbootpractice.restservices.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}
}
