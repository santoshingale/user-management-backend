package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import com.bridgelabz.usermanagement.exception.RegisterException;
import com.bridgelabz.usermanagement.model.UserData;
import com.bridgelabz.usermanagement.model.UserPermission;
import com.bridgelabz.usermanagement.repository.UserDataRepository;
import com.bridgelabz.usermanagement.repository.UserPermissionRepo;
import com.bridgelabz.usermanagement.response.Responce;
import com.bridgelabz.usermanagement.util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTTokenUtil jwtTokenUtil;
    String profilePic = "default_image.png";
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private UserPermissionRepo userPermissionRepo;
    @Autowired
    private FirebaseStorageStrategy firebaseStorageStrategy;

    public ResponseEntity register(UserDataDTO userDataDTO, MultipartFile profilePic) throws IOException {
        if (userDataRepository.findByEmail(userDataDTO.email).isPresent()) {
            throw new RegisterException("email already registered with us", RegisterException.ExceptionType.Email_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        if (userDataRepository.findByPhone(userDataDTO.phone).isPresent()) {
            throw new RegisterException("phone already registered with us", RegisterException.ExceptionType.NUMBER_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        if (userDataRepository.findByPhoneext(userDataDTO.phoneext).isPresent()) {
            throw new RegisterException("phone already registered with us", RegisterException.ExceptionType.NUMBER_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "successful submited", saveUserData(userDataDTO, profilePic)), HttpStatus.OK);
    }

    public ResponseEntity update(UserData userData, MultipartFile profilePic) throws IOException {

        Optional<UserData> byId = userDataRepository.findById(userData.getId());
        if (byId.isEmpty()) {
            throw new RegisterException("email not registered with us", RegisterException.ExceptionType.INVALID_RECORD, HttpStatus.BAD_REQUEST.value());
        }
        UserData userData1 = byId.get();
        userData.setRegistrationDate(userData1.getRegistrationDate());
        userData.setProfilePic(userData1.getProfilePic());
        userData.setLastUpdate(LocalDateTime.now());
        if (profilePic != null) {
            userData.setProfilePic(firebaseStorageStrategy.uploadFile(profilePic));
        }
        userDataRepository.deleteById(userData.getId());
        userPermissionRepo.deleteById(userData.getUserPermission().getId());
        return new ResponseEntity(new Responce(HttpStatus.OK.value(), "successful submited", userDataRepository.save(userData)), HttpStatus.OK);
    }

    @Transactional
    UserData saveUserData(UserDataDTO userDataDTO, MultipartFile pic) throws IOException {
        if (pic != null) {
            String profilePic = firebaseStorageStrategy.uploadFile(pic);
        }
        UserPermission userPermission = userPermissionRepo.save(new UserPermission((userDataDTO)));
        UserData userData = new UserData(userDataDTO);
        userData.setProfilePic(profilePic);
        userData.setUserPermission(userPermission);
        userData.setPassword(passwordEncoder.encode(userDataDTO.password));
        userData.setRegistrationDate(LocalDateTime.now());
        return userDataRepository.save(userData);
    }


    public ResponseEntity getUserCount(String... attribute) {
        if (attribute.length == 0) {
            return new ResponseEntity(new Responce(HttpStatus.OK.value()
                    , "successful", userDataRepository.getCountOfUser()), HttpStatus.OK);
        }
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "successful", userDataRepository.getCountOfSearchUser(attribute[0])), HttpStatus.OK);
    }


    public ResponseEntity getUserList(Integer listSize, Integer pagenumber) {
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "successful", userDataRepository.getUserDataForList(((pagenumber * listSize) - listSize), listSize)), HttpStatus.OK);
    }

    public ResponseEntity getUserListBySearchKey(Integer listSize, Integer pagenumber, String searchKey) {
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "successful", userDataRepository.getUserDataForListWithSearch(((pagenumber * listSize) - listSize), listSize, searchKey)), HttpStatus.OK);
    }

    public ResponseEntity getRecentRegistrationList() {
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "successful", userDataRepository.getRecentTegistration()), HttpStatus.OK);
    }

    public ResponseEntity getProfilePic(String imageName) throws Exception {
        return firebaseStorageStrategy.downloadFile(imageName);
    }

    public ResponseEntity deleteUser(Long id) {
        userDataRepository.deleteById(id);
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "user deleted successfully"), HttpStatus.OK);
    }

    public ResponseEntity handleLogout(Long id) {
        userDataRepository.updateStatus(id, "Inactive");
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "successful"), HttpStatus.OK);
    }

    public ResponseEntity getUserDetails(String token) {
        return new ResponseEntity(new Responce(HttpStatus.OK.value()
                , "successful", userDataRepository.findByEmail(jwtTokenUtil.getUsernameFromToken(token.substring(7)))), HttpStatus.OK);
    }
}
