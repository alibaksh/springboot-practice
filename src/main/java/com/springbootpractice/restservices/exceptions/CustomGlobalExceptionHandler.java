package com.springbootpractice.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From MethodArgumentNotValid Exception in GEH", ex.getLocalizedMessage(),
				HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupported Exception in GEH", ex.getLocalizedMessage(),
				HttpStatus.METHOD_NOT_ALLOWED.value());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleConstraintViolationException(UserNotFoundException ex, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleConstraintViolationException(NotFoundException ex, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				ex.getLocalizedMessage() + " doesn't exist, please provide valid id", request.getDescription(false),
				HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
}
