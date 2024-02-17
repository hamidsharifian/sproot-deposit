package com.tosan.entity;

public enum CustomerType {

	REAL(0),
	LEGAL(1);

	private int value;

	private CustomerType(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getStringValue(){
		return String.valueOf(value);
	}

}
