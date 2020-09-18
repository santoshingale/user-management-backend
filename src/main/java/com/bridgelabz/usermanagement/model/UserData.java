package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@Data
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid FistName")
    private String firstname;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid MiddleName")
    private String middlename;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid LastName")
    private String lastname;

    private Date dateOfBirth;

    @NotNull
    @Pattern(regexp = " ^(?:Male|Female)$", message = "Enter Valid Gender")
    private String gender;

    @NotNull
    private String country;

    @Column(unique = true, nullable = false)
    @NotNull
    @Pattern(regexp = "^[5-9]{1}[0-9]{9}$", message = "Enter Valid Mobile Number")
    private Long phone;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^[5-9]{1}[0-9]{9}$", message = "Enter Valid Mobile Number")
    private Long phoneext;
    @Column(unique = true, nullable = false)

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "enter valid email")
    private String email;
    private String address;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Enter Valid Name")
    private String username;

    @JsonIgnore
    private String password;

    private String role;

    private String profilePic;

    private LocalDateTime registrationDate;

    private LocalDateTime lastUpdate;

    private LocalDateTime lastLogin;

    @Column(columnDefinition = "int default 0")
    private Integer worongLoginAttempt;

    @Column(columnDefinition = "varchar(10) default 'Inactive'")
    @Pattern(regexp = " ^(?:Active|Inactive)$", message = "Enter Valid Gender")
    private String status;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private UserPermission userPermission;

    public UserData(UserDataDTO userDataDTO) {
        this.firstname = userDataDTO.firstname;
        this.middlename = userDataDTO.middlename;
        this.lastname = userDataDTO.lastname;
        this.gender = userDataDTO.gender;
        this.country = userDataDTO.country;
        this.phone = userDataDTO.phone;
        this.phoneext = userDataDTO.phoneext;
        this.email = userDataDTO.email;
        this.address = userDataDTO.address;
        this.username = userDataDTO.username;
        this.password = userDataDTO.password;
        this.role = userDataDTO.role;
        this.dateOfBirth = userDataDTO.dateOfBirth;
    }
}
