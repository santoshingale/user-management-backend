package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebPage3Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean addWebPage3;
    private boolean deleteWebPage3;
    private boolean modifyWebPage3;
    private boolean readWebPage3;

    public WebPage3Permission() {
    }

    public WebPage3Permission(UserDataDTO userDataDTO) {
        this.addWebPage3 = userDataDTO.addWebPage3;
        this.deleteWebPage3 = userDataDTO.deleteWebPage3;
        this.modifyWebPage3 = userDataDTO.modifyWebPage3;
        this.readWebPage3 = userDataDTO.readWebPage3;
    }
}
