package com.cognixia.jump.reqmodel;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class User_Transaction implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transaction_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//@JsonIgnoreProperties("User_Transaction")
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "user_Id", referencedColumnName = "user_Id", nullable = false )
	public User user;

	//@JsonIgnoreProperties("User_Transaction")
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false )
	public Product product;

	
	public User_Transaction() {
	
	}
	
	

	

	public User_Transaction(Integer id, User user, Product product ) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		
	}





	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

	
	


	@Override
	public String toString() {
		return "User_Transaction [id=" + id + ", user=" + user + ", product=" + product + ", amount=" + "]";
	}



	public void add(User_Transaction transaction) {
		// TODO Auto-generated method stub
		
	}


	


	

	
	
	

	
	
	
}
