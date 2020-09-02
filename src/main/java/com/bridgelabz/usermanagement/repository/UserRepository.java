package com.bridgelabz.usermanagement.repository;

import com.bridgelabz.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean findAllByEmail(String email);

    Optional<User> findByEmail(String email);
}