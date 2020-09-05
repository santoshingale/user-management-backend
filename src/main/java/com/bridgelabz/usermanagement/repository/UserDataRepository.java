package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
