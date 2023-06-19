package com.example.ecommercebackendproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;
    private String fullName;
}
