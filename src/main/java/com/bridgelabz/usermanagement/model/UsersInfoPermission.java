package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsersInfoPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean addUsersInformation;
    private boolean deleteUsersInformation;
    private boolean modifyUsersInformation;
    private boolean readUsersInformation;

    public UsersInfoPermission() {
    }

    public UsersInfoPermission(UserDataDTO userDataDTO) {
        this.addUsersInformation = userDataDTO.addUsersInformation;
        this.deleteUsersInformation = userDataDTO.deleteUsersInformation;
        this.modifyUsersInformation = userDataDTO.modifyUsersInformation;
        this.readUsersInformation = userDataDTO.readUsersInformation;
    }
}
