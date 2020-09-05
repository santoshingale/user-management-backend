package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import com.bridgelabz.usermanagement.model.UserData;
import com.bridgelabz.usermanagement.repository.UserDataRepository;
import com.bridgelabz.usermanagement.response.Responce;
import com.bridgelabz.usermanagement.service.UserDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/home/admin")
    ResponseEntity<Responce> addUser(@Valid @RequestBody UserDataDTO userDataDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Responce>(new Responce(HttpStatus.UNAUTHORIZED.value()
                    , bindingResult.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.UNAUTHORIZED);
        }

        UserData save = userDataService.register(userDataDTO);
        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "sucessully submited", save), HttpStatus.OK);
    }
}
