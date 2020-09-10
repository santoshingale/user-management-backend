package com.bridgelabz.usermanagement.controller;


import com.bridgelabz.usermanagement.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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

    @GetMapping(value = "/list", params = {"listLength","pageNumber"})
    public ResponseEntity getUserList(@RequestParam Integer listLength, @RequestParam Integer pageNumber) {
        return userDataService.getUserList(listLength, pageNumber);
    }

    @GetMapping(value = "/list" , params = {"listLength","pageNumber","searchKey"})
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
}
