package com.cognixia.jump.service;

import java.sql.PreparedStatement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ProductRepository;
import com.cognixia.jump.repository.TransactionRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.reqmodel.Record_Transactions;
import com.cognixia.jump.reqmodel.User_Transaction;


@Service
public class TransactionService {

	@Autowired
	ProductRepository productrepo;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	TransactionRepository transrepo;
	
	public boolean addTransaction(User_Transaction transaction) {
		if (transaction != null) {
			
			transrepo.save(transaction);
			return true;
		}
		
		return false;
	}
	
	public boolean addUserandProductToTransaction(int productId, int userId, int Id ) {
		
		Optional<User> useradd = userrepo.findById(userId);
		Optional<Product> productadd = productrepo.findById(productId);
		Optional<User_Transaction> transadd = transrepo.findById(Id);
		
		if (useradd.isPresent() && productadd.isPresent() && transadd.isPresent())  {
			
			useradd.get().addTransaction(transadd.get());
			transadd.get().setUser(useradd.get());
			
			userrepo.save(useradd.get());
			//transrepo.save(transadd.get());
			
			productadd.get().addTransaction(transadd.get());
			transadd.get().setProduct(productadd.get());
									
			productrepo.save(productadd.get());
			transrepo.save(transadd.get());
			
			return true;
		}
		
		return false;
	}
	
	
//	
//			public User_Transaction updateTransaction(Record_Transactions model) throws ResourceNotFoundException {
//					
//					// save() -> performs an update as long as the id exists
//					//		  -> if id doesn't exist, will do an insert
//					
//					if( transrepo.existsById(model.getId()) ) {
//						
//						User_Transaction updated = transrepo.save(model);
//						
//						return updated;
//					}
//					
//					throw new ResourceNotFoundException(" Transaction Not found ");
//				}
	
	
}
