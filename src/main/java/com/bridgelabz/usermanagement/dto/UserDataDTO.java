package com.bridgelabz.usermanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import javax.xml.crypto.Data;
import java.util.Date;

public class UserDataDTO {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid FistName")
    public String firstname;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid MiddleName")
    public String middlename;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid LastName")
    public String lastname;

    @NotNull
    @Pattern(regexp = " ^(?:Male|Female)$", message = "Enter Valid Gender")
    public String gender;

    @NotNull
    public String country;

    @NotNull
    @Pattern(regexp = "^[5-9]{1}[0-9]{9}$", message = "Enter Valid Mobile Number")
    public Long phone;

    @NotNull
    @Pattern(regexp = "^[5-9]{1}[0-9]{9}$", message = "Enter Valid Mobile Number")
    public Long phoneext;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "enter valid email")
    public String email;

    @NotNull
    public String address;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid Name")
    public String username;

    @NotNull
    public String password;

    @NotNull
    @Pattern(regexp = " ^(?:User|Admin)$", message = "Enter Valid role")
    public String role;

    public Date dateOfBirth;

    public boolean addDashboard;
    public boolean deleteDashboard;
    public boolean modifyDashboard;
    public boolean readDashboard;

    public boolean addSettings;
    public boolean deleteSettings;
    public boolean modifySettings;
    public boolean readSettings;

    public boolean addUsersInformation;
    public boolean deleteUsersInformation;
    public boolean modifyUsersInformation;
    public boolean readUsersInformation;

    public boolean addWebPage1;
    public boolean deleteWebPage1;
    public boolean modifyWebPage1;
    public boolean readWebPage1;

    public boolean addWebPage2;
    public boolean deleteWebPage2;
    public boolean modifyWebPage2;
    public boolean readWebPage2;

    public boolean addWebPage3;
    public boolean deleteWebPage3;
    public boolean modifyWebPage3;
    public boolean readWebPage3;


    public UserDataDTO(String firstname, String middlename, String lastname, String gender, String country, Long phone,
                       Long phoneext, String email, String address, String username, String password, String role,
                       boolean addDashboard, boolean deleteDashboard, boolean modifyDashboard,
                       boolean readDashboard, boolean addSettings, boolean deleteSettings, boolean modifySettings, boolean readSettings,
                       boolean addUsersInformation, boolean deleteUsersInformation, boolean modifyUsersInformation, boolean readUsersInformation,
                       boolean addWebPage1, boolean deleteWebPage1, boolean modifyWebPage1, boolean readWebPage1, boolean addWebPage2,
                       boolean deleteWebPage2, boolean modifyWebPage2, boolean readWebPage2, boolean addWebPage3, boolean deleteWebPage3,
                       boolean modifyWebPage3, boolean readWebPage3 , Date dateOfBirth) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.gender = gender;
        this.country = country;
        this.phone = phone;
        this.phoneext = phoneext;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
        this.addDashboard = addDashboard;
        this.deleteDashboard = deleteDashboard;
        this.modifyDashboard = modifyDashboard;
        this.readDashboard = readDashboard;
        this.addSettings = addSettings;
        this.deleteSettings = deleteSettings;
        this.modifySettings = modifySettings;
        this.readSettings = readSettings;
        this.addUsersInformation = addUsersInformation;
        this.deleteUsersInformation = deleteUsersInformation;
        this.modifyUsersInformation = modifyUsersInformation;
        this.readUsersInformation = readUsersInformation;
        this.addWebPage1 = addWebPage1;
        this.deleteWebPage1 = deleteWebPage1;
        this.modifyWebPage1 = modifyWebPage1;
        this.readWebPage1 = readWebPage1;
        this.addWebPage2 = addWebPage2;
        this.deleteWebPage2 = deleteWebPage2;
        this.modifyWebPage2 = modifyWebPage2;
        this.readWebPage2 = readWebPage2;
        this.addWebPage3 = addWebPage3;
        this.deleteWebPage3 = deleteWebPage3;
        this.modifyWebPage3 = modifyWebPage3;
        this.readWebPage3 = readWebPage3;
        this.dateOfBirth = dateOfBirth;
    }

    public UserDataDTO() {
    }
}
