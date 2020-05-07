package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.user.service.IUser;

public class UserDTO {

	@Autowired
	IUser userService;

	@NotEmpty(message = "first name must not be empty")
	@Size(min = 3, message = "last name cannot be less than 3 characters")
	private String firstName;

	@NotNull
	@Size(min = 3, message = "last name cannot be less than 3 characters")
	private String lastName;

	@NotNull
	private String email;

	@NotNull(message = "Please select a password")
	private String password;

	private String confirmPassword;

	public UserDTO(String firstName, String lastname, String email, String password, String confirmPassword) {

		this.firstName = firstName;
		this.lastName = lastname;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;

	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
