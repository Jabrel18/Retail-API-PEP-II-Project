package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;



@RestController
@RequestMapping("/api")
public class UserController {

	
	
	//private static final Role ROLE_USER = null;

	//private static final Role ROLE_ADMIN = null;

	@Autowired
	UserService service;
	
	@Autowired 
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/user")
	public List<User> getUsers() {
				
		return repo.findAll();
	}
	
	

	// CREATE
	@PostMapping("/user")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		if (service.addUser(user)) {
			return new ResponseEntity<>("Created user: " + user, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Failed to create user: " + user, HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		
		user.setId(-1);
		
		
		// each password
		user.setPassword( encoder.encode( user.getPassword() ) );
		
		User created = repo.save(user);
		
		return ResponseEntity.status(201).body(created);
		
	
		
	}
	
	
	
	
}
