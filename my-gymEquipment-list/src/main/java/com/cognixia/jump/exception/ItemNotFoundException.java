package com.cognixia.jump.exception;

public class ItemNotFoundException extends Exception{


	private static final long serialVersionUID = 1L;
	// Exception class
	
	public ItemNotFoundException(int id) {
		super("Item not Found with id = " + id );
	}

	
	
	
}
