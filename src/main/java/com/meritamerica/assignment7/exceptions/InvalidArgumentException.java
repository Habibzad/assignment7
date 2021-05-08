package com.meritamerica.assignment7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends Exception {

	private static final long serialVersionUID = -8844280408836992565L;

	public InvalidArgumentException(String message) {
		super(message);
	}
}
