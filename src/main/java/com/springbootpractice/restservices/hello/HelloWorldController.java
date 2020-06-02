package com.springbootpractice.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

//	@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World of Spring boot";
	}
	
	@GetMapping(path = "/helloworld-bean")
	public UserDetailsBean helloWorldBean() {
		return new UserDetailsBean("Ali", "Mehmood", "Karachi-PK");
	}
}
