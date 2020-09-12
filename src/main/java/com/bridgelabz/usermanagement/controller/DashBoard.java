package com.bridgelabz.usermanagement.controller;

import com.bridgelabz.usermanagement.service.DashboardService;
import com.bridgelabz.usermanagement.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/dashboard")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DashBoard {

    @Autowired
    DashboardService dashboardService;

    @Autowired
    UserDataService userDataService;

    @GetMapping(value = "", params = "status")
        public ResponseEntity getNoOfUsers (@RequestParam String status){
            return dashboardService.getActiveUser(status);
        }
}
