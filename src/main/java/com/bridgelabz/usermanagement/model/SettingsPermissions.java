package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SettingsPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean addSettings;
    private boolean deleteSettings;
    private boolean modifySettings;
    private boolean readSettings;

    public SettingsPermissions() {
    }

    public SettingsPermissions(UserDataDTO userDataDTO) {
        this.addSettings = userDataDTO.addSettings;
        this.deleteSettings = userDataDTO.deleteSettings;
        this.modifySettings = userDataDTO.modifySettings;
        this.readSettings = userDataDTO.readSettings;
    }
}
