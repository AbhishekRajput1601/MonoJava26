package com.abhi.tictactoeusingfacade;

public class InvalidChoiceException extends RuntimeException {

    public InvalidChoiceException(String message) {
        super(message);
    }
}
