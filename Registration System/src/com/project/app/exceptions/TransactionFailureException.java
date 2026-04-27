package com.project.app.exceptions;

public class TransactionFailureException extends RuntimeException {
    public TransactionFailureException() { super(); }
    public TransactionFailureException(String message) { super(message); }
    public TransactionFailureException(String message, Throwable cause) { super(message, cause); }
}

