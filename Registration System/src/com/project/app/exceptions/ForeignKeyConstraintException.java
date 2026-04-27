package com.project.app.exceptions;

public class ForeignKeyConstraintException extends DataAccessException {
    public ForeignKeyConstraintException() { super(); }
    public ForeignKeyConstraintException(String message) { super(message); }
    public ForeignKeyConstraintException(String message, Throwable cause) { super(message, cause); }
}

