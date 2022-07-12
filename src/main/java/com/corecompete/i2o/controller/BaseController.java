package com.corecompete.i2o.controller;

import brave.Tracer;
import com.corecompete.i2o.constants.ErrorMessageConstants;
import com.corecompete.i2o.exception.EventsCustomException;
import com.corecompete.i2o.utility.ErrorResponse;
import com.corecompete.i2o.utility.EventsConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Component
@Slf4j
public class BaseController {

	@Autowired
	private Tracer tracer;
	
	@ExceptionHandler(EventsCustomException.class)
	public ResponseEntity<ErrorResponse> customExceptionHandler(EventsCustomException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setCode(ex.getCode());
		response.setMessage(ex.getMessage());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if (ErrorMessageConstants.INVALID_REQUEST.equalsIgnoreCase(ex.getCode())) {
			log.info(EventsConstants.CUSTOM_EXCEPTION_RESPONE + response);
			
		}
		if (ErrorMessageConstants.INTERNAL_SERVER_ERROR.equalsIgnoreCase(ex.getCode())) {
			log.error(EventsConstants.CUSTOM_EXCEPTION_RESPONE + response);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		if (ErrorMessageConstants.NOT_FOUND.equalsIgnoreCase(ex.getCode())) {
			log.error(EventsConstants.CUSTOM_EXCEPTION_RESPONE + response);
			status = HttpStatus.NOT_FOUND;
		}
		String logRef = "";
		if (tracer.currentSpan() != null) {
			logRef = tracer.currentSpan().context().traceIdString();
		}
		response.setLogref(logRef);
	    return new ResponseEntity<>(response, status);
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorResponse> sqlExceptionHandler(SQLException ex) {
		log.error("SQL Exception Handler", ex);
		ErrorResponse response = new ErrorResponse();
		response.setCode(ErrorMessageConstants.INTERNAL_SERVER_ERROR);
		response.setMessage("Please Contact Administrator");
		String logRef = "";
		if (tracer.currentSpan() != null) {
			logRef = tracer.currentSpan().context().traceIdString();
		}
		response.setLogref(logRef);
	    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setCode(ErrorMessageConstants.INVALID_REQUEST);
		response.setMessage("Invalid request body sent, "+ex.getMessage());
		String logRef = "";
		if (tracer.currentSpan() != null) {
			logRef = tracer.currentSpan().context().traceIdString();
		}
		response.setLogref(logRef);
		log.info("HttpMessageNotReadableException {}", ex);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorResponse> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		ErrorResponse response = new ErrorResponse();
		response.setCode(ErrorMessageConstants.INVALID_REQUEST);
		response.setMessage("Request body sent with wrong Content type, it should be application/json");
		String logRef = "";
		if (tracer.currentSpan() != null) {
			logRef = tracer.currentSpan().context().traceIdString();
		}
		response.setLogref(logRef);
		log.info("HttpMessageNotReadableException {}", ex);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception ex) {
		log.error("Generic Exception Handler", ex);
		ErrorResponse response = new ErrorResponse();
		response.setCode(ErrorMessageConstants.INTERNAL_SERVER_ERROR);
		response.setMessage("Please Contact Administrator");
		String logRef = "";
		if (tracer.currentSpan() != null) {
			logRef = tracer.currentSpan().context().traceIdString();
		}
		response.setLogref(logRef);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
