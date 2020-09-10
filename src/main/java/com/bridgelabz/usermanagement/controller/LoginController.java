package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dto.LogInDTO;
import com.bridgelabz.usermanagement.dto.TokenResponseDTO;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.model.UserData;
import com.bridgelabz.usermanagement.response.Responce;
import com.bridgelabz.usermanagement.service.IUserService;
import com.bridgelabz.usermanagement.util.JWTTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    IUserService iUserService;

    @PostMapping("/login")
    ResponseEntity<Responce> logInUser(@Valid @RequestBody LogInDTO logInDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Responce>(new Responce(HttpStatus.UNAUTHORIZED.value()
                    , bindingResult.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.UNAUTHORIZED);
        }
        return iUserService.login(logInDTO);
    }

    @GetMapping("/home")
    ResponseEntity getHomePage() {
        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "home page"), HttpStatus.OK);
    }

    @GetMapping("/forgetpassword")
    ResponseEntity forgetPassword(@RequestParam("email") String email) throws MessagingException {
        if (!email.isEmpty()) {
            iUserService.resetPassword(email);
            return new ResponseEntity(new Responce(HttpStatus.OK.value(), "Verification link has been send to your email address"), HttpStatus.OK);
        }
        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "email should not empty"), HttpStatus.BAD_REQUEST);
    }
}
