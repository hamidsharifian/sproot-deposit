package com.tosan.exceptions;

// class representing custom exception
public class CustomerHasDepositException extends Exception
{
    public CustomerHasDepositException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}