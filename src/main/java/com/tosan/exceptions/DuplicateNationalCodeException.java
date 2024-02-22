package com.tosan.exceptions;

// class representing custom exception
public class DuplicateNationalCodeException  extends Exception
{
    public DuplicateNationalCodeException (String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}