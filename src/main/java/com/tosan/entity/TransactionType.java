package com.tosan.entity;

public enum TransactionType {

	WITHRAW(0),
	DEPOSIT(1),
	TRANSFER(3);

	private int value;

	private TransactionType(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getStringValue(){
		return String.valueOf(value);
	}

}
