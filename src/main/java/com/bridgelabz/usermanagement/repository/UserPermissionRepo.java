package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionRepo extends JpaRepository<UserPermission, Long> {
}
