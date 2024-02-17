package com.tosan.entity;

public enum Currency {

	IRR(0),
	USD(1),
	EUR(2);

	private int value;

	private Currency(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getStringValue(){
		return String.valueOf(value);
	}

}
