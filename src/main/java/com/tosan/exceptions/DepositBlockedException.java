package com.tosan.exceptions;

// class representing custom exception
public class DepositBlockedException extends Exception
{

    public DepositBlockedException() {
        super("The deposit is blocked!");
    }

    public DepositBlockedException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}