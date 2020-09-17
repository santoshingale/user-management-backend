package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.repository.UserDataRepository;
import com.bridgelabz.usermanagement.response.Responce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    UserDataRepository userDataRepository;

    public ResponseEntity getActiveUser(String status) {
        if (status.equals("active")) {
            return new ResponseEntity(new Responce(HttpStatus.OK.value()
                    , "sucessully", userDataRepository.getActiveUserCount()), HttpStatus.OK);
        }
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "sucessully", userDataRepository.getInactiveUserCount()), HttpStatus.OK);
    }

    public ResponseEntity getOnlineUser() {
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "sucessully", userDataRepository.getOnlineUserCount()), HttpStatus.OK);
    }

    public ResponseEntity getUserAscByRegistrationDate() {
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "sucessully", userDataRepository.getUserAscByRegistrationDate()), HttpStatus.OK);
    }
}
