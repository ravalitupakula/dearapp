package com.google.dearapp.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DuplicateEmailIdEsception extends RuntimeException {
	private String message;

//	public DuplicateEmailIdEsception() {
//
//	}
//
//	public DuplicateEmailIdEsception(String message) {
//		this.message = message;
//	}
	
	
	public String getMessage() {
		return this.message;
	}
}
