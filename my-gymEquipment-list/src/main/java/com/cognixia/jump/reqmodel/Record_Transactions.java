package com.cognixia.jump.reqmodel;

public class Record_Transactions {
	
	private Integer id;
	private int userId;
	private int productId;
	private Long amount;
	
	
	
	public Record_Transactions() {
		this.userId = (int) -1L;
		this.productId = (int) -1L;
	}
	
	
	
	public Record_Transactions(Integer id, int userId, int productId, Long amount) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	


	@Override
	public String toString() {
		return "Record_Transactions [id=" + id + ", userId=" + userId + ", productId=" + productId + ", amount="
				+ amount + "]";
	}
	
	
	
	

}
