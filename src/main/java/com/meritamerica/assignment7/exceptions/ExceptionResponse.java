package com.meritamerica.assignment7.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String details;
}
