package com.neondomain.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public static final String SECRET_KEY = "THISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEYTHISISASECRETKEY";

	public String getToken(UserDetails user) {
		
		return getToken(new HashMap<>(), user);
	}
	
	private String getToken(Map<String, Object> extraCalims, UserDetails user) {
		return Jwts
				.builder()
				.setClaims(extraCalims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}

	public String getUserFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = getUserFromToken(token);
		
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private Claims getAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		
		return claimsResolver.apply(claims);
	}
	
	private Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}
	
}
