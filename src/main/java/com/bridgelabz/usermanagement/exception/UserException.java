package com.bridgelabz.usermanagement.exception;

import lombok.Data;

@Data
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
}
