package com.example.ecommercebackendproject.model.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RegistrationForm {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String fullName;
}
