package com.example.ecommercebackendproject.model.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class LoginForm {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
