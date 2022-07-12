package com.corecompete.i2o.exception;

public class EventsCustomException extends RuntimeException {

	 private static final long serialVersionUID = -8147400539216572008L;
	    
	    private final String code;

	    public EventsCustomException(String code, String message) {
	        super(message);
	        this.code = code;
	    }

	    public String getCode() {
	        return code;
	    }
	
}
