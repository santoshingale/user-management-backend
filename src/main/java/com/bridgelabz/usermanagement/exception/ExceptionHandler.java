package com.bridgelabz.usermanagement.exception;

import com.bridgelabz.usermanagement.response.Responce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserException.class)
    ResponseEntity<Responce> userException(UserException userException) {
        return new ResponseEntity<>(new Responce(userException.getStatusCode(),userException.getMessage()), HttpStatus.resolve(userException.getStatusCode()));
    }
}
