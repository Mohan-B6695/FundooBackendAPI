package com.bridgelabz.fundoo.user.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.exception.VerificationFailedException;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.service.IUser;
import com.bridgelabz.fundoo.utility.Response;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUser iUser;

	@PostMapping("/register")
	public ResponseEntity<Response> Register(@Valid @RequestBody UserDTO userDTO) {

		Response response = iUser.register(userDTO);

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping("/verrifyUser/{token}")
	public ResponseEntity<Response> verifyUser(@PathVariable String token) throws VerificationFailedException {
		Response response = iUser.verifyUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO) {

		Response response = iUser.login(loginDTO);
		return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);

	}

	@PutMapping("/forgotPassword")
	public ResponseEntity<Response> forgetPassword(@RequestParam String email) {

		Response response = iUser.forgetPassword(email);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/resetPassword/{token}")
	public ResponseEntity<Response> resetPassword(@PathVariable String token,
			@Valid @RequestBody ForgetPasswordDTO forgetPassword) throws VerificationFailedException {

		Response response = iUser.resetPassword(token, forgetPassword);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
