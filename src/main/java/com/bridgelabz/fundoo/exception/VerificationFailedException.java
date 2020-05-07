package com.bridgelabz.fundoo.exception;

@SuppressWarnings("serial")
public class VerificationFailedException extends RuntimeException {

	String massage;

	public VerificationFailedException() {
		super();
	}
	
	public VerificationFailedException(String massage) {
		super(massage);
		
	}
	
	
}
