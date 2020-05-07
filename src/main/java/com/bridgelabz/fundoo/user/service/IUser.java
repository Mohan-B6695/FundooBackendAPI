package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.exception.VerificationFailedException;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;

public interface IUser {

	public Response register(UserDTO userDto);

	public Response login(LoginDTO loginDTO) throws UserDoesNotExistException;

	public Response forgetPassword(String email) throws UserDoesNotExistException;

	public Response verifyUser(String token) throws VerificationFailedException;

	public Response resetPassword(String token, ForgetPasswordDTO forgetPasswordDTO) throws VerificationFailedException;

}
