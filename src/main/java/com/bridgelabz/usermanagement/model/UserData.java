package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Setter
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private Date dateOfBirth;
    private String gender;
    private String country;

    @Column(unique = true, nullable = false)
    private Long phone;
    @Column(unique = true)
    private Long phoneext;
    @Column(unique = true, nullable = false)
    private String email;
    private String address;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String role;
    private String profilePic;

    @OneToOne(cascade = CascadeType.MERGE)
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
        this.dateOfBirth = userDataDTO.dadateOfBirth;
    }
}
