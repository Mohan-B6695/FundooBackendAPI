package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO {

	@NotNull
	@Email
	@Size(min=8 ,max=40)
	private String email;
	
	@NotNull
	@Size(min=8,max=30)
	private String password;

	public LoginDTO(@Email @Size(min = 8, max = 40) String email, @Size(min = 8, max = 30) String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
