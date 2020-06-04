package com.springbootpractice.restservices.exceptions;

import java.util.Date;

public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errorDetails;
	private int statusCode;

	public CustomErrorDetails(Date timestamp, String message, String errorDetails, int statusCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
		this.statusCode = statusCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorDetails() {
		return errorDetails;
	}
	
	public int getStatus() {
		return statusCode;
	}
}
