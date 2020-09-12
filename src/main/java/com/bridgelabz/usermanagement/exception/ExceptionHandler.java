package com.bridgelabz.usermanagement.exception;

import com.bridgelabz.usermanagement.response.Responce;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = LoginException.class)
    ResponseEntity<Responce> userException(LoginException loginException) {
        return new ResponseEntity<>(new Responce(loginException.getStatusCode(), loginException.getMessage()), HttpStatus.resolve(loginException.getStatusCode()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = JsonMappingException.class)
    public ResponseEntity<Responce> handleConverterErrors(LoginException loginException) { // Or whatever exception type you want to handle
        return new ResponseEntity<>(new Responce(loginException.getStatusCode(), loginException.getMessage()), HttpStatus.resolve(loginException.getStatusCode()));
    }
}
