package com.tosan.entity;

public enum DepositStatus {

	OPEN(0),
	CLOSED(1),
	BLOCKED(2),
	WITHRAW_BLOCKED(3),
	DEPOSIT_BLOCKED(4);

	private int value;

	private DepositStatus(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getStringValue(){
		return String.valueOf(value);
	}

}
