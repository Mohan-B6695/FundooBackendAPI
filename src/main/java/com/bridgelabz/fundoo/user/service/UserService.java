
package com.bridgelabz.fundoo.user.service;

import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.exception.VerificationFailedException;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.utility.TokenUtil;

@PropertySource(name = "user", value = { "classpath:response.properties", "classpath:userupdate.properties" })
@Service
public class UserService implements IUser {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Environment environment;

	@Autowired
	private PasswordEncoder passwordEncode;

	@Autowired
	private TokenUtil tokenutil;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Response register(UserDTO userDto) throws UserDoesNotExistException {

		Optional<User> userCheck = userRepository.findByEmail(userDto.getEmail());
		if (!userCheck.isPresent()) {
			if (userDto.getPassword().equals(userDto.getConfirmPassword())) {
				passwordEncode.encode(userDto.getPassword());
				User user = modelMapper.map(userDto, User.class);
				user.setRegisteredDate(LocalDateTime.now());
				user.setPassword(passwordEncode.encode(userDto.getPassword()));
				user.setUpdatedDate(LocalDateTime.now());
				userRepository.save(user);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(userDto.getEmail());
				mailMessage.setFrom("bogathamohan@gmail.com");
				mailMessage.setSubject("valid user check");
				String token = tokenutil.createToken(user.getUserid());
				mailMessage.setText("verification link " + " http://192.168.0.140:8080/users/verrifyUser/" + token);
				javaMailSender.send(mailMessage);
				return new Response(LocalDateTime.now(), HttpStatus.OK.value(),
									environment.getProperty("status.register.success"), token);
			}
			throw new UserDoesNotExistException(environment.getProperty("status.register.incorrectpassword"));
		}
		throw new UserDoesNotExistException(environment.getProperty("status.register.already.exists"));
	}

	@Override
	public Response login(LoginDTO loginDTO) throws UserDoesNotExistException {

		Optional<User> userCheck = userRepository.findByEmail(loginDTO.getEmail());
		if (!userCheck.isPresent()) {
			throw new UserDoesNotExistException(environment.getProperty("status.login.userexist"));
		} else if (passwordEncode.matches(loginDTO.getPassword(), userCheck.get().getPassword())) {
			String token = tokenutil.createToken(userCheck.get().getUserid());
			System.out.println(token);
			return new Response(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
								environment.getProperty("status.login.success"), token);
		} else if (!userCheck.get().getPassword().equals(loginDTO.getPassword())) {

			throw new UserDoesNotExistException(environment.getProperty("status.login.incorrectpassword"));
		}

		throw new UserDoesNotExistException(environment.getProperty("status.login.usernotexist"));
	}

	@Override
	public Response verifyUser(String token) throws VerificationFailedException {

		Long id = tokenutil.decodeToken(token);
		Optional<User> verifyuser = userRepository.findById(id);
		if (verifyuser.isPresent()) {
			verifyuser.get().setVerify("verified");
			userRepository.save(verifyuser.get());
			throw new VerificationFailedException(environment.getProperty("status.login.verifiedSucceed"));
		}
		return new Response(LocalDateTime.now(), HttpStatus.ACCEPTED.value(),
							environment.getProperty("status.login.verificationFailed"), null);
	}

	@Override
	public Response forgetPassword(String email) throws UserDoesNotExistException {
		
		Optional<User> checkEmail = userRepository.findByEmail(email);
		if (checkEmail.isPresent()) {
			userRepository.save(checkEmail.get());
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("bogathamohan@gmail.com");
			mailMessage.setSubject("valid user check");
			String token = tokenutil.createToken(checkEmail.get().getUserid());
			mailMessage.setText("verification link " + "http://192.168.0.140:8080/users/resetPassword/" + token);
			javaMailSender.send(mailMessage);
			throw new UserDoesNotExistException(environment.getProperty("status.user.exists"));
		}
		return new Response(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
							environment.getProperty("status.login.usernotexist"), null);
	}

	@Override
	public Response resetPassword(String token, ForgetPasswordDTO forgetPassword) throws VerificationFailedException {

		Long id = tokenutil.decodeToken(token);
		Optional<User> verifyuser = userRepository.findById(id);
		String password = forgetPassword.getPassword();
		String ConfirmPassword = forgetPassword.getConfirmPassword();
		if (password.equals(ConfirmPassword)) {
			verifyuser.get().setPassword(passwordEncode.encode(password));
			verifyuser.get().setUpdatedDate(LocalDateTime.now());
			userRepository.save(verifyuser.get());
			throw new VerificationFailedException(environment.getProperty("status.update.password"));
		}
		return new Response(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
							environment.getProperty("status.resetpassword.incorrectpassword"), null);
	}
}
