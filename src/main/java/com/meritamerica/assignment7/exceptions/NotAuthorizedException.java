package com.meritamerica.assignment7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends Exception {
	private static final long serialVersionUID = -362006227411972428L;

	public NotAuthorizedException(String message) {
		super(message);
	}
}
