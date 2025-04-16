package com.BankU.backend.service;

import com.BankU.backend.model.User;
import com.BankU.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Aggiunto PasswordEncoder

    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Confronta la password inserita con quella crittografata nel database
            return passwordEncoder.matches(password, user.getPassword());
        }
        
        return false;
    }
}