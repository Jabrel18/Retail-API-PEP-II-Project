package com.cognixia.jump.repository;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ //JpaRepository< table, 

	// custom query for finding users by ussername
	// important for security, security willonly know how to find a user by the username
	// Optional -> possibility that no user exists with this username, so we represent that with an optional(could be null)
	public Optional<User> findByUsername(String username);

	public Object findUserByEmail(String email);

//	@Query("SELECT u FROM user u WHERE u.user_id = {id}")
//	public int getById();



	
	
	
}