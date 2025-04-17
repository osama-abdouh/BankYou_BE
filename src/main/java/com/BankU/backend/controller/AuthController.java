package com.BankU.backend.controller;

import java.util.Collections;
import com.BankU.backend.model.LoginRequest;
import com.BankU.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

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