package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@Data
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean addDashboard;
    private boolean deleteDashboard;
    private boolean modifyDashboard;
    private boolean readDashboard;

    private boolean addSettings;
    private boolean deleteSettings;
    private boolean modifySettings;
    private boolean readSettings;

    private boolean addUsersInformation;
    private boolean deleteUsersInformation;
    private boolean modifyUsersInformation;
    private boolean readUsersInformation;

    private boolean addWebPage1;
    private boolean deleteWebPage1;
    private boolean modifyWebPage1;
    private boolean readWebPage1;

    private boolean addWebPage2;
    private boolean deleteWebPage2;
    private boolean modifyWebPage2;
    private boolean readWebPage2;

    private boolean addWebPage3;
    private boolean deleteWebPage3;
    private boolean modifyWebPage3;
    private boolean readWebPage3;

    public UserPermission() {
    }

    public UserPermission(UserDataDTO userDataDTO) {
        this.addDashboard = userDataDTO.addDashboard;
        this.deleteDashboard = userDataDTO.deleteDashboard;
        this.modifyDashboard = userDataDTO.modifyDashboard;
        this.readDashboard = userDataDTO.readDashboard;

        this.addSettings = userDataDTO.addSettings;
        this.deleteSettings = userDataDTO.deleteSettings;
        this.modifySettings = userDataDTO.modifySettings;
        this.readSettings = userDataDTO.readSettings;

        this.addUsersInformation = userDataDTO.addUsersInformation;
        this.deleteUsersInformation = userDataDTO.deleteUsersInformation;
        this.modifyUsersInformation = userDataDTO.modifyUsersInformation;
        this.readUsersInformation = userDataDTO.readUsersInformation;

        this.addWebPage1 = userDataDTO.addWebPage1;
        this.deleteWebPage1 = userDataDTO.deleteWebPage1;
        this.modifyWebPage1 = userDataDTO.modifyWebPage1;
        this.readWebPage1 = userDataDTO.readWebPage1;

        this.addWebPage2 = userDataDTO.addWebPage2;
        this.deleteWebPage2 = userDataDTO.deleteWebPage2;
        this.modifyWebPage2 = userDataDTO.modifyWebPage2;
        this.readWebPage2 = userDataDTO.readWebPage2;

        this.addWebPage3 = userDataDTO.addWebPage3;
        this.deleteWebPage3 = userDataDTO.deleteWebPage3;
        this.modifyWebPage3 = userDataDTO.modifyWebPage3;
        this.readWebPage3 = userDataDTO.readWebPage3;
    }
}
