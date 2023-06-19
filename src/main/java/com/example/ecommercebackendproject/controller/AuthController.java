package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.dto.LoginForm;
import com.example.ecommercebackendproject.model.dto.RegistrationForm;
import com.example.ecommercebackendproject.model.entity.User;
import com.example.ecommercebackendproject.service.AuthService;
import com.example.ecommercebackendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?>register(@Valid @RequestBody RegistrationForm registrationForm) throws Exception {
        return authService.register(registrationForm);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) throws Exception {
        return authService.login(loginForm);
    }

}
