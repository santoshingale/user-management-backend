package com.bridgelabz.usermanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime loginTime;

    public LoginHistory(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LoginHistory() {
    }
}
