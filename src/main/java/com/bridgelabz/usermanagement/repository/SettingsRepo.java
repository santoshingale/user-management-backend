package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.SettingsPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<SettingsPermissions, Long> {
}
