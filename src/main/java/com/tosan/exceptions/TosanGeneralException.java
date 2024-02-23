package com.tosan.exceptions;

// class representing custom exception
public class TosanGeneralException extends Exception
{
    public TosanGeneralException(String message)
    {
        // calling the constructor of parent Exception
        super(message);
    }
}