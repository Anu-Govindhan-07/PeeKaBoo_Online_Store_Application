package com.peekaboo.user.dto;

public class LoginResponseDto {

    private String token;
    private String message;
    private String email;
    private String role;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String token, String message, String email, String role) {
        this.token = token;
        this.message = message;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}