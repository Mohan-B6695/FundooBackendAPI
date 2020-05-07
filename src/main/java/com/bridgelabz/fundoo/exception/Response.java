package com.bridgelabz.fundoo.exception;

import java.time.LocalDateTime;

public class Response {

	private LocalDateTime dateTime;
	private int Status;
	private String message;

	public Response(LocalDateTime dateTime, int status, String message) {
		super();
		this.dateTime = dateTime;
		Status = status;
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
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

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [dateTime=" + dateTime + ", Status=" + Status + ", message=" + message + "]";
	}
}
