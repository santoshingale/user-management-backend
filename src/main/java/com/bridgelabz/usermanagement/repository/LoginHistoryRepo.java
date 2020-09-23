package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepo extends JpaRepository<LoginHistory, Long> {
}
