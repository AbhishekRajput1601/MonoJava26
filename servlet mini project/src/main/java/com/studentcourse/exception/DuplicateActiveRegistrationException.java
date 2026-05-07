package com.studentcourse.exception;

public class DuplicateActiveRegistrationException extends Exception {
    public DuplicateActiveRegistrationException(String message) {
        super(message);
    }

    public DuplicateActiveRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}

