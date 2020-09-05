package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebPage2Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean addWebPage2;
    private boolean deleteWebPage2;
    private boolean modifyWebPage2;
    private boolean readWebPage2;

    public WebPage2Permission() {
    }

    public WebPage2Permission(UserDataDTO userDataDTO) {
        this.addWebPage2 = userDataDTO.addWebPage2;
        this.deleteWebPage2 = userDataDTO.deleteWebPage2;
        this.modifyWebPage2 = userDataDTO.modifyWebPage2;
        this.readWebPage2 = userDataDTO.readWebPage2;
    }
}
