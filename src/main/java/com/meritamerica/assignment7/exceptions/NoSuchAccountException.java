package com.meritamerica.assignment7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchAccountException extends Exception {
	private static final long serialVersionUID = 7594908583738908517L;

	public NoSuchAccountException(String message) {
		super(message);
	}
}
