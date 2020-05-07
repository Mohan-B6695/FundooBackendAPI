package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class ForgetPasswordDTO {
	
	@NotNull(message="Please select a password")
	@Length(min=8, max=15, message="Password should be between 8 - 15 charactes")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",
			 message = "password has digit,lower case,upper case and special character "
			 + "must occur at least once,Password should be between 8 - 15 charactes")
	private String password;

    private String confirmPassword;

	public ForgetPasswordDTO(
			@NotNull(message = "Please select a password") @Length(min = 8, max = 15, message = 
					 "Password should be between 8 - 15 charactes") 
					 @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",
					 message = "password has digit,lower case,upper case and special character must occur at "
				   	 + "least once,Password should be between 8 - 15 charactes") String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	@Override
	public String toString() {
		return "ForgetPasswordDTO [password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
	
}
