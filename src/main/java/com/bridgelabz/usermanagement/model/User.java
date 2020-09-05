package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.SignUpDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String email;
    public String name;
    public String lName;
    public String password;

    public User() {
    }

    public User(SignUpDTO signUpDTO) {
        this.email = signUpDTO.email;
        this.name = signUpDTO.name;
        this.lName = signUpDTO.lName;
        this.password = signUpDTO.password;
    }

}
