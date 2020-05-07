package com.bridgelabz.fundoo.utility;

import java.time.LocalDateTime;

public class Response {

	private LocalDateTime dateTime;
	private int Status;
	private String message;
	private String token;

	public Response(LocalDateTime dateTime, int status, String message, String token) {
		super();
		this.dateTime = dateTime;
		Status = status;
		this.message = message;
		this.token = token;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String massage) {
		this.message = massage;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
