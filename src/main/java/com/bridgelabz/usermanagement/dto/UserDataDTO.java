package com.bridgelabz.usermanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

    @NotNull
    public String profilePic;

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
}
