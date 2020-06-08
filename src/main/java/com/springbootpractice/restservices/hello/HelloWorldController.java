package com.springbootpractice.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World of Spring boot";
	}

	@GetMapping(path = "/helloworld-bean")
	public UserDetailsBean helloWorldBean() {
		return new UserDetailsBean("Ali", "Mehmood", "Karachi-PK");
	}

	@GetMapping(path = "/helloworld-i18")
	public String getMessagesIni18Format(
			@RequestHeader(name = "Accept-Language", required = false, defaultValue = "en") String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}

	@GetMapping(path = "/helloworld-i182")
	public String getMessagesIni18Format2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
