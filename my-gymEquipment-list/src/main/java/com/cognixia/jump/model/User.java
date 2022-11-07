package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.cognixia.jump.reqmodel.User_Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User implements Serializable{


	private static final long serialVersionUID = 1L;

	
	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}
	
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // incrementation will use auto_increment
	private Integer id;
	
	@Column( unique = true, nullable = false )
	private String username;
	
	@Column( nullable = false )
	private String password;
	
	@Column( nullable = false)
	private String email;
	
	// will store the role as a string in the db
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column( columnDefinition= "boolean default true")
	private boolean enabled;
	
	//@JsonIgnoreProperties
	@JsonBackReference
	@OneToOne(mappedBy = "user", fetch=FetchType.EAGER, targetEntity = User_Transaction.class)
	private Set<User_Transaction> user_transaction = new HashSet<>();
	
//	@JsonIgnoreProperties("user")
//	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	@JoinTable(
//			name = "user_transacstions",
//			joinColumns = {@JoinColumn(name = "user_id")},
//			inverseJoinColumns = {@JoinColumn(name = "product_id")
//									}								
//	)
//	private Set<Product> products = new HashSet<>(); 
	

	
	public User() {
		
		this.id = -1;
		this.username = "N/A";
		this.password = "N/A";
		this.email = "N/A";
		this.user_transaction = null;
	}

	
	

	


	public User(Integer id, String username, String password, String email, Role role, boolean enabled,
			Set<User_Transaction> user_transaction) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enabled = enabled;
		this.user_transaction = user_transaction;
	}







	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getUsername() {
		return username;
	}






	public void setUsername(String username) {
		this.username = username;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public Role getRole() {
		return role;
	}






	public void setRole(Role role) {
		this.role = role;
	}






	public boolean isEnabled() {
		return enabled;
	}






	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public Set<User_Transaction> getUser_transaction() {
		return user_transaction;
	}







	public void setUser_transaction(Set<User_Transaction> user_transaction) {
		this.user_transaction = user_transaction;
	}







	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + ", enabled=" + enabled + ", user_transaction=" + user_transaction + "]";
	}







	public void addTransaction(User_Transaction transaction) {
		// TODO Auto-generated method stub
		this.user_transaction.add(transaction);
		
	}







	
	
	
	
	
	
	
}
