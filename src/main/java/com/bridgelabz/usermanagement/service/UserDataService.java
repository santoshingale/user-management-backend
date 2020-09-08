package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import com.bridgelabz.usermanagement.exception.RegisterException;
import com.bridgelabz.usermanagement.model.UserData;
import com.bridgelabz.usermanagement.model.UserPermission;
import com.bridgelabz.usermanagement.repository.UserDataRepository;
import com.bridgelabz.usermanagement.repository.UserPermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private UserPermissionRepo userPermissionRepo;
    @Autowired
    private FirebaseStorageStrategy firebaseStorageStrategy;

    public UserData register(UserDataDTO userDataDTO, MultipartFile profilePic) throws IOException {
        if (userDataRepository.findByEmail(userDataDTO.email).isPresent()) {
            throw new RegisterException("email not registered with us", RegisterException.ExceptionType.Email_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        if (userDataRepository.findByPhone(userDataDTO.phone).isPresent()) {
            throw new RegisterException("phone not registered with us", RegisterException.ExceptionType.NUMBER_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        if (userDataRepository.findByPhoneext(userDataDTO.phoneext).isPresent()) {
            throw new RegisterException("phone not registered with us", RegisterException.ExceptionType.NUMBER_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        return saveUserData(userDataDTO, profilePic);
    }

    @Transactional
    UserData saveUserData(UserDataDTO userDataDTO, MultipartFile pic) throws IOException {
        String profilePic = firebaseStorageStrategy.uploadFile(pic);
        UserPermission userPermission = userPermissionRepo.save(new UserPermission((userDataDTO)));
        UserData userData = new UserData(userDataDTO);
        userData.setProfilePic(profilePic);
        userData.setUserPermission(userPermission);
        return userDataRepository.save(userData);
    }
}
