package com.tosan.exceptions;

// class representing custom exception
public class TransactionFailedException extends Exception
{
    public TransactionFailedException() {
        super("Transaction failed, Try again!");
    }

    public TransactionFailedException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}