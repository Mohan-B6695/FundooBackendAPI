package com.bridgelabz.fundoo.utility;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {

	public final String tokenSecret = "mohan";

	public String createToken(Long id) {
		try {
			Algorithm algo = Algorithm.HMAC256(tokenSecret);
			String token = JWT.create().withClaim("id", id).sign(algo);
			return token;

		} catch (JWTCreationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Long decodeToken(String token) {
		Long userid;
		Verification verification = null;
		try {
			verification = JWT.require(Algorithm.HMAC256(tokenSecret));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		JWTVerifier jwtverifier = verification.build();
		DecodedJWT decojwt = jwtverifier.verify(token);
		Claim claim = decojwt.getClaim("id");
		userid = claim.asLong();
		return userid;

	}

}
