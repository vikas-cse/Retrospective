package com.sis.retrospective.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sis.retrospective.exception.GenericException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder errorMessage = new StringBuilder("Validation failed due to below reasons : \n");
		for (ObjectError error : e.getAllErrors()) {
			errorMessage.append(error.getCodes()[0] + " " + error.getDefaultMessage() + "\n");
		}
		return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GenericException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleGenericException(GenericException e) {
		return new ResponseEntity<>("Failed due to error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return new ResponseEntity<>("Failed due to error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
