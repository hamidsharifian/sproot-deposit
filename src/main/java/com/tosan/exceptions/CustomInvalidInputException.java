package com.tosan.exceptions;

import java.util.List;

public class CustomInvalidInputException extends Exception {
    public List<String> messages;
    public CustomInvalidInputException(String mainMessage, List<String> messages)
    {
        // calling the constructor of parent Exception
        super(mainMessage);
        this.messages = messages;
    }
}
