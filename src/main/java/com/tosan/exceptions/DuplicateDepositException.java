package com.tosan.exceptions;

// class representing custom exception
public class DuplicateDepositException extends Exception
{
    public DuplicateDepositException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}