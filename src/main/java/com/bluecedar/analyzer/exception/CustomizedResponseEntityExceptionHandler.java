package com.bluecedar.analyzer.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
/**
 * 
 * @author Ramu Enugala
 *
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
