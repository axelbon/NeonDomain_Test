package com.neondomain.entity;

public class AuthResponse {
	String token;
	
	public AuthResponse() {	}
	
	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
