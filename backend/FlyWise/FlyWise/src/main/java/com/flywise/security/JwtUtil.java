package com.flywise.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// import com.flywise.repository.AppUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Autowired
	private Environment env;

	
	private String createToken(Map<String, Object> info) {
		
		long curTime = System.currentTimeMillis();
		
		long expiration = env.getProperty("jwt.token.expiration.millis", Long.class);
		
		String secret = env.getProperty("jwt.token.secret");
		
		
		return Jwts.builder()
				.setSubject(null)
				.setClaims(info)
				.setIssuedAt(new Date(curTime))
				.setExpiration(new Date(curTime + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	
	public String generateToken(String userName,Integer userId,String role) {
		
		Map<String,Object> info = new HashMap<String, Object>();
		
		info.put("email", userName);
		
		info.put("userId", userId);
		
		info.put("role",role);
		
		return createToken(info);
	}
	
	
	private Claims decodeToken(String token) {
		
		String secret = env.getProperty("jwt.token.secret");
		
		return Jwts.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token)
			.getBody();
	}
	
	
	public String getTokenUsername(String token) {
		
		Claims claims = decodeToken(token);
		
		String userName = claims.get("email", String.class);
		
		return userName;
	}
	
	
	public Integer getTokenUserId(String token) {
		
		Claims claims = decodeToken(token);
		
		Integer userId = claims.get("userId", Integer.class);
		
		return userId;
	}
	
	
	public boolean validateToken(String token, String userName) {
		
		Claims claims = decodeToken(token);
		
		if(!claims.get("email").equals(userName))
			return false;
		
		if(claims.getExpiration().before(new Date()))
			return false;
		
		return true;
	}
	
}
