package com.BankU.backend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "Si è verificato un errore. Controlla l'URL o contatta l'amministratore.";
    }
}
