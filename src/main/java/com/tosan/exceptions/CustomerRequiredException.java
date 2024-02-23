package com.tosan.exceptions;

// class representing custom exception
public class CustomerRequiredException extends Exception
{
    public CustomerRequiredException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}