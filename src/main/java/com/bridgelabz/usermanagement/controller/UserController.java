package com.bridgelabz.usermanagement.controller;


import com.bridgelabz.usermanagement.dto.UserDataDTO;
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
@RequestMapping("/home/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserDataService userDataService;

    @GetMapping("/count")
    public ResponseEntity getNoOfUsers() {
        return userDataService.getUserCount();
    }

    @GetMapping(value = "/count", params = "searchKey")
    public ResponseEntity getNoOfUsersBySearch(@RequestParam String searchKey) {
        return userDataService.getUserCount(searchKey);
    }

    @GetMapping(value = "/list", params = {"listLength", "pageNumber"})
    public ResponseEntity getUserList(@RequestParam Integer listLength, @RequestParam Integer pageNumber) {
        return userDataService.getUserList(listLength, pageNumber);
    }

    @GetMapping(value = "/list", params = {"listLength", "pageNumber", "searchKey"})
    public ResponseEntity getUserListBySearch(@RequestParam Integer listLength, @RequestParam Integer pageNumber, @RequestParam String searchKey) {
        return userDataService.getUserListBySearchKey(listLength, pageNumber, searchKey);
    }

    @GetMapping(value = "/image/{imageName}")
    public ResponseEntity getUserListBySearch(@PathVariable String imageName) throws Exception {
        return userDataService.getProfilePic(imageName);
    }

    @GetMapping(value = "/delete", params = "id")
    public ResponseEntity deleteUser(@RequestParam Long id) throws Exception {
        return userDataService.deleteUser(id);
    }

    @PostMapping("/update")
     ResponseEntity<Responce> updateUserData(@Valid @RequestPart("update") String update, @RequestPart("profilePic") MultipartFile profilePic, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Responce>(new Responce(HttpStatus.UNAUTHORIZED.value()
                    , bindingResult.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.UNAUTHORIZED);
        }
        UserDataDTO userDataDTO = new ObjectMapper().readValue(update, UserDataDTO.class);

        return userDataService.register(userDataDTO, profilePic);
    }
}
