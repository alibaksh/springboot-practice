package com.springbootpractice.restservices.exceptions;

public class NotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2534695535124635280L;

	public NotFoundException(String message) {
		super(message);
		message = message + " doesn't exist, please provide valid id";
	}

}
