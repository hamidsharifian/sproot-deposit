package com.tosan.entity;

public enum DepositType {

	ONGOING(0),
	SAVING(1),
	LONGTERM(2),
	SHORTTERM(3);


	private int value;

	private DepositType(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getStringValue(){
		return String.valueOf(value);
	}

}
