package com.education.madkouresta.madkouresta.sec;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class TokenUtiles {

	private long TOKEN_VALIDITY = 604800L; // 7 day
	private String TOKEN_SECRET = "shopping-api";

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> clims = new HashMap<>();
		clims.put("sub", userDetails.getUsername());
		clims.put("created", new Date());

		return Jwts.builder().setClaims(clims).setExpiration(getExpriationDate())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
	}

	
	private Date getExpriationDate() {

		return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
	}

	public String getUserNameFromToken(String token) {

		Claims claims = getClaims(token);

		return claims.getSubject();

	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = getUserNameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	private Claims getClaims(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
		} catch (Exception ex) {
			claims = null;
		}

		return claims;
	}
}
