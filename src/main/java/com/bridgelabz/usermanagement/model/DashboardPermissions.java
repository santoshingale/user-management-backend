package com.bridgelabz.usermanagement.model;

import com.bridgelabz.usermanagement.dto.UserDataDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class DashboardPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean addDashboard;
    private boolean deleteDashboard;
    private boolean modifyDashboard;
    private boolean readDashboard;

    public DashboardPermissions() {
    }

    public DashboardPermissions(UserDataDTO userDataDTO) {
        this.addDashboard = userDataDTO.addDashboard;
        this.deleteDashboard = userDataDTO.deleteDashboard;
        this.modifyDashboard = userDataDTO.modifyDashboard;
        this.readDashboard = userDataDTO.readDashboard;
    }

}
