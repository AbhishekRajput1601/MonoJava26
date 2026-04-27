package com.project.app.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException() { super(); }
    public DataAccessException(String message) { super(message); }
    public DataAccessException(String message, Throwable cause) { super(message, cause); }
    public DataAccessException(Throwable cause) { super(cause); }
}

