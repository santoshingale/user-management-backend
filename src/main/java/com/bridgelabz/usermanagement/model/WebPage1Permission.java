package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebPage1Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean addWebPage1;
    private boolean deleteWebPage1;
    private boolean modifyWebPage1;
    private boolean readWebPage1;

    public WebPage1Permission() {
    }

    public WebPage1Permission(UserDataDTO userDataDTO) {
        this.addWebPage1 = userDataDTO.addWebPage1;
        this.deleteWebPage1 = userDataDTO.deleteWebPage1;
        this.modifyWebPage1 = userDataDTO.modifyWebPage1;
        this.readWebPage1 = userDataDTO.readWebPage1;
    }
}
