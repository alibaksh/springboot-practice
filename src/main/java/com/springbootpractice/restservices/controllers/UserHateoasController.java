package com.springbootpractice.restservices.controllers;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootpractice.restservices.entities.Order;
import com.springbootpractice.restservices.entities.User;
import com.springbootpractice.restservices.exceptions.UserNotFoundException;
import com.springbootpractice.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/hateoas/users")
public class UserHateoasController {

	@Autowired
	private UserService userService;

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> users = userService.getAllUsers();
		for (User user : users) {
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel();
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrdersByUserId(user.getId());
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(selfLink);
			user.add(ordersLink);
		}
		Link selfLinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalEntities = CollectionModel.of(users, selfLinkAllUsers);

		return finalEntities;
	}

	@GetMapping(path = "/{id}")
	public EntityModel<User> getUserById(@PathVariable @Min(1) Long id) throws UserNotFoundException {
		User user = userService.getUserById(id).get();
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel();
		user.add(selfLink);
		EntityModel<User> finalEntity = EntityModel.of(user);

		CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
				.getAllOrdersByUserId(user.getId());

		Link allOrdersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
		user.add(allOrdersLink);

		return finalEntity;

	}
}
