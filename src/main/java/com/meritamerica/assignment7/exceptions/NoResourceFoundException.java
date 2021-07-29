package com.meritamerica.assignment7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResourceFoundException extends Exception {
	private static final long serialVersionUID = -4955070135244048671L;

	public NoResourceFoundException(String msg) {
		super(msg);
	}
}