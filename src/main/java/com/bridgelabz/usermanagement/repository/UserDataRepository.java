package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);

    Optional<Object> findByPhone(Long phone);

    Optional<Object> findByPhoneext(Long phoneext);
}
