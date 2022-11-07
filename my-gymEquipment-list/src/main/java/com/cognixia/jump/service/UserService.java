package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
		
		if (userRepo.findByUsername(user.getUsername()).isPresent()) {
			return true;
		}
		
		return false;
	}
	
	

}
