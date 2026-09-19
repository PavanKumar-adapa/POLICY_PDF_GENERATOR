package com.policy.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<String> policyNotFoundExceptionHadler(PolicyNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PdfGenerationException.class)
	public ResponseEntity<String> pdfExceptionHandler(PdfGenerationException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> pdfExceptionHandler(Exception ex) {
		return new ResponseEntity<>("Hey something wrong with your request ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
