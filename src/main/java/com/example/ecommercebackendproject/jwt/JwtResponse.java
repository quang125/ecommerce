package com.example.ecommercebackendproject.jwt;

public class JwtResponse {
    private String role;
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String name;


    public JwtResponse(String accessToken, String role, String username, String name) {
        this.accessToken = accessToken;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}