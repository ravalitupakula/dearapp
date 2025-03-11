package com.google.dearapp.handlingexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.dearapp.exceptionclasses.DuplicateEmailIdEsception;
import com.google.dearapp.exceptionclasses.DuplicatePhoneException;
import com.google.dearapp.exceptionclasses.InvalidUserIdException;
import com.google.dearapp.responsestructure.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(DuplicateEmailIdEsception.class)
	public ResponseStructure<String> duplicateEmailIdEsception(DuplicateEmailIdEsception e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Already Acoount Exit For This Email");
		structure.setBody(e.getMessage());
		return structure;
	}
	
	
	//********************************************************************************************
	
	@ExceptionHandler(DuplicatePhoneException.class)
	public ResponseStructure<String> duplicatePhoneException(DuplicatePhoneException e){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Already Acoount Exit For This Phone Number");
		structure.setBody(e.getMessage());
		return structure;
	}
	
	//*********************************************************************************************
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseStructure<String> invalidUserIdException(InvalidUserIdException e){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Invalid Id give Valid Id");
		structure.setBody(e.getMessage());
		return structure;
	}

}
