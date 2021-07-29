package com.meritamerica.assignment7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeAmountException extends Exception {

	private static final long serialVersionUID = -3176394067918000928L;

	public NegativeAmountException() {
		super();
	}
}