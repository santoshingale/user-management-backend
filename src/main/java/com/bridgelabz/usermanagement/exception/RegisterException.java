package com.bridgelabz.usermanagement.exception;

import lombok.Data;


@Data
public class RegisterException extends RuntimeException {
    private ExceptionType type;
    private int statusCode;

    public RegisterException(String message, ExceptionType type, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public enum ExceptionType {
        Email_ALREADY_EXIST, NUMBER_ALREADY_EXIST,
    }
}
