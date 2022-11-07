package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ItemNotFoundException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;
import com.cognixia.jump.repository.TransactionRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.reqmodel.Record_Transactions;
import com.cognixia.jump.reqmodel.User_Transaction;
import com.cognixia.jump.service.TransactionService;

@RequestMapping("/api")
@RestController
public class ProductController {
	
	@Autowired
	UserRepository user;
	
	@Autowired
	ProductRepository repo;
	
	@Autowired
	TransactionRepository trans;
	
	@Autowired
	TransactionService service;
	
	@GetMapping("/product")
	public List<Product> getProducts() {
		return repo.findAll();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable int id) throws ItemNotFoundException {
		
		Optional<Product> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ItemNotFoundException(id);
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		
		product.setId(-1);
		
		Product created = repo.save(product);
		
		return ResponseEntity.status(201).body(created);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) throws ResourceNotFoundException {
		
		Optional<Product> found = repo.findById(id);
		
		if( found.isEmpty() ) {
			
			throw new ResourceNotFoundException("Product with id = " + id + " was not found");
		}
		
		repo.deleteById(id);
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@PutMapping("/product")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) throws ResourceNotFoundException {
		
		if(repo.existsById(product.getId())) {
			
			Product updated = repo.save(product);
			
			return ResponseEntity.status(200).body(updated);
		}
		
		throw new ResourceNotFoundException("Product with id = " + product.getId() + " was not found");
	}
	
	
	@PostMapping("/purchase/{id}/{userid}")
	public ResponseEntity<?> createTransaction( @PathVariable int id, @PathVariable int userid,  User_Transaction model ) {
		
		//trans.createTransactionID();
		
		model.setId(-1);
			repo.findProductById(id);
		model.setId(id);
			user.findById(userid);
		model.user.setId(userid);
	
		
		User_Transaction created = trans.save(model);
		
		return ResponseEntity.status(201).body(created);
				
		
	}
	
	
	@PostMapping("/purchases/purchase/{id}/{userid}")
	public ResponseEntity<?> recordProductPurchase( @PathVariable int id, @PathVariable int userid, @RequestBody Record_Transactions model ) {
		
			Optional<User_Transaction> found = trans.findById(model.getId());
		
			model.setId(-1);
				repo.findProductById(id);
			model.setProductId(id);
				user.findById(userid);
			model.setUserId(userid);
	
		
		
		if ( service.addUserandProductToTransaction(
				-1, id, userid)) {
			
			
				
				return new ResponseEntity<>(" purchase completed ", HttpStatus.CREATED)
						
						; 
			}		
		
//			if(trans.existsById(id)) {
//					
//			User_Transaction updated = trans.save(model);
//						
//			return  new ResponseEntity<>(" purchase completed ", HttpStatus.CREATED);
//					}

			

		
			return new ResponseEntity<>("Failed to complete purchase. ", HttpStatus.NOT_ACCEPTABLE)
					 ;
		
	
	}
	
	
	
	@GetMapping("/purchase/{id}")
	public ResponseEntity<?> getTransaction(@PathVariable int id) throws ItemNotFoundException {
		
		Optional<User_Transaction> found = trans.findById(id);
		
		if(found.isEmpty()) {
			throw new ItemNotFoundException(id);
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
//	@PutMapping
//	public ResponseEntity<?> updateTransaction( @RequestBody Record_Transactions model ) {
//		
//		
//		
//		Record_Transactions updated = service.updateTransaction(model);
//		
//		return ResponseEntity.status(200).body(updated);
//	}
	
	
	
	
	}
	













