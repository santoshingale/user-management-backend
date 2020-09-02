package com.bridgelabz.usermanagement.model;

import lombok.Data;

@Data
public class Email {
    private String id;
    private String message;
    private String subject;

    public Email(String id, String subject, String message) {
        this.id = id;
        this.message = message;
        this.subject = subject;
    }

    public Email() {
    }
}
