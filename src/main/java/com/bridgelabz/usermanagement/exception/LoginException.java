package com.bridgelabz.usermanagement.exception;

import lombok.Data;

@Data
public class LoginException extends RuntimeException {
    private ExceptionType type;
    private int statusCode;

    public LoginException(String message, ExceptionType type, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public enum ExceptionType {
        WRONG_EMAIL, INVALID_TOKEN, RESET_PASSWORD,
    }
}
