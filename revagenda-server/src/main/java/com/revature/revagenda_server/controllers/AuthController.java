package com.revature.revagenda_server.controllers;

import com.revature.revagenda_server.dtos.RegistrationDto;
import com.revature.revagenda_server.models.User;
import com.revature.revagenda_server.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationDto registrationDto) {
        return authService.register(registrationDto);
    }
}