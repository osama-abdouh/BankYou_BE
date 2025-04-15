package com.BankU.backend.controller;

import com.BankU.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // consenti richieste da Angular
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public boolean login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }
}