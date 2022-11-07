package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.reqmodel.Record_Transactions;
import com.cognixia.jump.reqmodel.User_Transaction;



@Repository
public interface TransactionRepository extends JpaRepository<User_Transaction, Integer> {


	




	




}