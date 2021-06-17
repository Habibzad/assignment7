//package com.meritamerica.assignment7.controller;
//
//import org.springdoc.api.ErrorMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import com.meritamerica.assignment7.exceptions.InvalidArgumentException;
//
//@RestControllerAdvice
//public class ControllerExceptionHandler {
//	@ExceptionHandler(value = { InvalidArgumentException.class })
//	@ResponseStatus(value = HttpStatus.FORBIDDEN)
//	public ErrorMessage resourceNotFoundException(InvalidArgumentException ex, WebRequest request) {
//		ErrorMessage message = new ErrorMessage(ex.getMessage());
//		return message;
//	}
//}
