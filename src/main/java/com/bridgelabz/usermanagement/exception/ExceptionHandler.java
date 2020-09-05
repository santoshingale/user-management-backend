package com.bridgelabz.usermanagement.exception;

import com.bridgelabz.usermanagement.response.Responce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = LoginException.class)
    ResponseEntity<Responce> userException(LoginException loginException) {
        return new ResponseEntity<>(new Responce(loginException.getStatusCode(), loginException.getMessage()), HttpStatus.resolve(loginException.getStatusCode()));
    }
}
