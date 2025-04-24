package com.BankU.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BankU.backend.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);  // Changed from User to Optional<User>
}