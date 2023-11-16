package com.neondomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neondomain.entity.AuthResponse;
import com.neondomain.entity.LoginRequest;
import com.neondomain.entity.User;
import com.neondomain.repository.UserRepository;


@Service
public class AuthService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtService jwtService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	
	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User userDetails = userRepository.findByUserName(request.getUsername()).orElseThrow();

		String token = jwtService.getToken(userDetails);

		AuthResponse authResponse = new AuthResponse(token);

		return authResponse;
	}

}
