package com.neondomain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neondomain.entity.User;
import com.neondomain.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		return user;
	}

	public User createUser(User user) {
		Optional<User> userExist = userRepository.findByUserName(user.getUserName());
		if(userExist.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User already in database");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userRepository.save(user);
		
		return newUser;
	}
	
	public User updateUser(int id, User user) throws ResponseStatusException {
		Optional<User> userData = userRepository.findById(id);
		
		if(userData.isPresent()) {
			User _user = userData.get();
			
			_user.setAge(user.getAge());
			_user.setName(user.getName());
			_user.setUserName(user.getUserName());
			_user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			return userRepository.save(_user);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
	
	public String deleteUser(int id) throws ResponseStatusException{
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "User deleted successfully";
	}
	
}
