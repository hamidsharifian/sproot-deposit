package com.tosan.exceptions;

// class representing custom exception
public class InsufficientBalanceException extends Exception
{

    public InsufficientBalanceException() {
        super("The deposit balance is insufficient");
    }

    public InsufficientBalanceException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}