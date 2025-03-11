package com.google.dearapp.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class InvalidUserIdException extends RuntimeException {
	private String message;

	public String getMessage() {
		return this.message;
	}
}
