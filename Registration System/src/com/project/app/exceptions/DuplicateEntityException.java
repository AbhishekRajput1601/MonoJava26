package com.project.app.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException() { super(); }
    public DuplicateEntityException(String message) { super(message); }
    public DuplicateEntityException(String message, Throwable cause) { super(message, cause); }
}

