package com.google.dearapp.exceptionclasses;

public class InvalidOTPException extends RuntimeException {
 private String message;
public InvalidOTPException() {
	 
 }
 public InvalidOTPException(String message) {
	 this.message = message;
 }
 public String getMessage() {
	 return this.message;
 }
}
