package com.bluecedar.analyzer.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
/**
 * 
 * @author Ramu Enugala
 *
 */

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private HttpStatus statuscode;
	
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(HttpStatus statuscode) {
		this.statuscode = statuscode;
	}
	
	  public ErrorDetails(Date timestamp, String message, HttpStatus internalServerError) {
		    super();
		    this.timestamp = timestamp;
		    this.message = message;
		    this.statuscode = internalServerError;
	}
	
}
