package com.BankU.backend.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.BankU.backend.model.LoginRequest;
import com.BankU.backend.model.UserRegistrationDTO;
import com.BankU.backend.repository.UserRepository;
import com.BankU.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.BankU.backend.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody UserRegistrationDTO dto) {
    System.out.println("Ricevuto DTO: " + dto);

    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Email già registrata");
        return ResponseEntity.badRequest().body(error);
    }

    if (userRepository.findByCodiceFiscale(dto.getCodiceFiscale()).isPresent()) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Codice fiscale già registrato");
        return ResponseEntity.badRequest().body(error);
    }

    User user = new User();
    user.setNome(dto.getNome());
    user.setCognome(dto.getCognome());
    user.setEmail(dto.getEmail());
    user.setCodiceFiscale(dto.getCodiceFiscale());
    user.setDataNascita(dto.getDataNascita());
    user.setGenere(dto.getGenere());
    user.setTelefono(dto.getTelefono());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));

    userRepository.save(user);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Registrazione completata con successo");
    return ResponseEntity.ok(response);
}


    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(Collections.singletonMap("token", "fake-jwt-token"));
        }
        return ResponseEntity.status(401).body("Credenziali non valide");
    }
}