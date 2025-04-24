package com.BankU.backend.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "imrangay";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Password criptata: " + encodedPassword);
    }
}