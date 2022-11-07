package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cognixia.jump.reqmodel.User_Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {


	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column( unique = true, nullable = false )
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int qty;
	
	//@JsonIgnoreProperties
	@JsonBackReference
	@OneToOne(mappedBy = "product", fetch=FetchType.EAGER, targetEntity = User_Transaction.class)
	private Set<User_Transaction> user_transaction = new HashSet<>();
	
	
	public Product() {
		this(-1, "N/A", 0, 0, null);
	}

	public Product(Integer id, String name, double price, int qty, Set<User_Transaction> user_transaction ) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.user_transaction = user_transaction;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", qty=" + qty + "]";
	}

	public void addTransaction(User_Transaction transaction) {
		// TODO Auto-generated method stub
		this.user_transaction.add(transaction);
		
	}
	
	
	
}
