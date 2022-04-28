package com.payInCard.payInCardApp.Security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	private String secret = "Secret Word";
	private long expireTime = 3600000;
	
	public String generateToken(String username) {
		
		String token = Jwts
				
							.builder()
							.setSubject(username)
							.setIssuedAt(new Date())
							.setExpiration(new Date(System.currentTimeMillis()+expireTime))
							.signWith(SignatureAlgorithm.HS512, secret)
							.compact();
		return token;
	}
	
	public boolean validateToken(String token) {
		try {		
			Jwts	
			.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUsername(String token) {
		String username = Jwts
				.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody().getSubject();
		
		return username;
	}
}
