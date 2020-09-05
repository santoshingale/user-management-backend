package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.UsersInfoPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UsersInfoPermission, Long> {
}
