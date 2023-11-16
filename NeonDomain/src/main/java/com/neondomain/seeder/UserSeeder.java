package com.neondomain.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.neondomain.entity.User;
import com.neondomain.repository.UserRepository;

@Component
public class UserSeeder implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("seederName", 22, "admin", "$2a$10$uxnjF1wlRtcFuVvf3KzLVukH.sR2l3KkHCDES3x/Yl5POzh.DwG4m");
		
		userRepository.save(user);
	}

}
