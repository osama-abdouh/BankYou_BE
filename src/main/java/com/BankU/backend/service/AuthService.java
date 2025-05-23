
package com.BankU.backend.service;

import com.BankU.backend.model.User;
import com.BankU.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Log per vedere la password che l'utente sta inserendo e quella memorizzata nel DB
            logger.info("Password inserita: {}", password);
            logger.info("Password nel DB: {}", user.getPassword());

            // Confronta la password inserita con quella crittografata nel database
            boolean isAuthenticated = passwordEncoder.matches(password, user.getPassword());
            logger.info("Autenticazione riuscita: {}", isAuthenticated);

            return isAuthenticated;
        }
        
        logger.warn("Utente non trovato con username: {}", username);
        return false;
    }
}