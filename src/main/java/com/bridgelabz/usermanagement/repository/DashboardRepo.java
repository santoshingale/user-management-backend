package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.DashboardPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepo extends JpaRepository<DashboardPermissions, Long> {
}
