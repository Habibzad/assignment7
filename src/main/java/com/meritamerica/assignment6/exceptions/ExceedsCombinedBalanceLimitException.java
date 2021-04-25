package com.meritamerica.assignment6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceedsCombinedBalanceLimitException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8865158680542915898L;

	public ExceedsCombinedBalanceLimitException() {
		super();
	}

	public ExceedsCombinedBalanceLimitException(String message) {
		super(message);
	}

}
