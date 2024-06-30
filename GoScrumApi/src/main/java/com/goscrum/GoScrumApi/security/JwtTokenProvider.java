package com.GoScrum.GoScrumApi.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-in-ms}")
	private long jwtExpirationDate;

	// generate JWT token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();

		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(currentDate)
				.setExpiration(expirationDate)
				.signWith(key())
				.compact();

		return token;
	}


	// get username from JWT token
	public String getUsername(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody();
		String username = claims.getSubject();
		return username;
	}

	// validate JWT token
	public boolean validateToken(String token) {

		try {
			Jwts.parser()
					.verifyWith(key())
					.build()
					.parseSignedClaims(token)
					.getPayload();
			return true;
		} catch (JwtException ex) {
			return false;
		}
		// } catch (MalformedJwtException ex) {
		//     // throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		//     throw new Exception("Invalid JWT token");
		// } catch (ExpiredJwtException ex) {
		//  throw new Exception("Expired JWT token");
		//     // throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		// } catch (UnsupportedJwtException ex) {
		// throw new Exception("Unsupported JWT token");
		//     // throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		//
		// } catch (IllegalArgumentException ex) {
		//     // throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
		// throw new Exception("JWT claims string is empty.");
		// }
	}

	private SecretKey key() {
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret)
		);
	}
}
