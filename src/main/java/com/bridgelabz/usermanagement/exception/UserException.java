package com.bridgelabz.usermanagement.exception;

public class UserException extends RuntimeException {
    private ExceptionType type;
    private int statusCode;
    public enum ExceptionType{
        WRONG_EMAIL, INVALIDE_TOKEN,
    }

    public UserException(String message, ExceptionType type, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ExceptionType getType() {
        return type;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
