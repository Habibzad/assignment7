package com.meritamerica.assignment7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ReachedAccountLimitException extends Exception{
	private static final long serialVersionUID = 8304270056192235356L;

	public ReachedAccountLimitException(String message) {
		super(message);
	}
}
