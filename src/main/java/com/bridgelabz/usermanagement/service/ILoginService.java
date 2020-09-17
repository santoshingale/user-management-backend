package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.LogInDTO;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface ILoginService {

    ResponseEntity login(LogInDTO logInDTO);

    boolean verifyJWTToken(String token);

    boolean resetPassword(String email) throws MessagingException;
}
