package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import com.bridgelabz.usermanagement.model.UserData;
import com.bridgelabz.usermanagement.response.Responce;
import com.bridgelabz.usermanagement.service.UserDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/home/register")
    ResponseEntity<Responce> addUser(@Valid @RequestPart("register") String register, @RequestPart("profilePic") MultipartFile profilePic, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Responce>(new Responce(HttpStatus.UNAUTHORIZED.value()
                    , bindingResult.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.UNAUTHORIZED);
        }
        UserDataDTO userDataDTO = new ObjectMapper().readValue(register, UserDataDTO.class);

        UserData save = userDataService.register(userDataDTO, profilePic);
        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "sucessully submited", save), HttpStatus.OK);
    }

//    @PostMapping("/home/image/upload")
//    ResponseEntity<Responce> uploadProfilePic( @RequestPart("file") MultipartFile file  ) {
//        String s = imageUpload.uploadImage(file);
//        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "sucessully submited", s), HttpStatus.OK);
//    }
}
