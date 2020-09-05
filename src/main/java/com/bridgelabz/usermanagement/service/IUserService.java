package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.LogInDTO;
import com.bridgelabz.usermanagement.model.User;

import javax.mail.MessagingException;

public interface IUserService {

    User login(LogInDTO logInDTO);

    boolean verifyJWTToken(String token);

    boolean resetPassword(String email) throws MessagingException;
}
