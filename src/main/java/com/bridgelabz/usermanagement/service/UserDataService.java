package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import com.bridgelabz.usermanagement.exception.RegisterException;
import com.bridgelabz.usermanagement.model.*;
import com.bridgelabz.usermanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private DashboardRepo dashboardRepo;
    @Autowired
    private SettingsRepo settingsRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private WebPage1Repo webPage1Repo;
    @Autowired
    private WebPage2Repo webPage2Repo;
    @Autowired
    private WebPage3Repo webPage3Repo;

    public UserData register(UserDataDTO userDataDTO) {
        if (userDataRepository.findByEmail(userDataDTO.email).isPresent()) {
            throw new RegisterException("email not registered with us", RegisterException.ExceptionType.Email_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        if (userDataRepository.findByPhone(userDataDTO.phone).isPresent()) {
            throw new RegisterException("phone not registered with us", RegisterException.ExceptionType.NUMBER_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        if (userDataRepository.findByPhoneext(userDataDTO.phoneext).isPresent()) {
            throw new RegisterException("phone not registered with us", RegisterException.ExceptionType.NUMBER_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
        }
        return saveUserData(userDataDTO);
    }

    @Transactional
    UserData saveUserData(UserDataDTO userDataDTO) {
        DashboardPermissions dashboardPermissions = dashboardRepo.save(new DashboardPermissions(userDataDTO));
        SettingsPermissions settingsPermissions = settingsRepo.save(new SettingsPermissions(userDataDTO));
        UsersInfoPermission usersInfoPermission = userInfoRepo.save(new UsersInfoPermission(userDataDTO));
        WebPage1Permission webPage1Permission = webPage1Repo.save(new WebPage1Permission(userDataDTO));
        WebPage2Permission webPage2Permission = webPage2Repo.save(new WebPage2Permission(userDataDTO));
        WebPage3Permission webPage3Permission = webPage3Repo.save(new WebPage3Permission(userDataDTO));
        UserData userData = new UserData(userDataDTO);
        userData.setDashboardPermissions(dashboardPermissions);
        userData.setSettingsPermissions(settingsPermissions);
        userData.setUsersInfoPermission(usersInfoPermission);
        userData.setWebPage1Permission(webPage1Permission);
        userData.setWebPage2Permission(webPage2Permission);
        userData.setWebPage3Permission(webPage3Permission);
        return userDataRepository.save(userData);
    }
}
