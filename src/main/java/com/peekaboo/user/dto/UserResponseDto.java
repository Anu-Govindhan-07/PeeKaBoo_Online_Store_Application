package com.peekaboo.user.dto;

public class UserResponseDto {

    private Long id;
    private String email;
    private String role;
    private Boolean isActive;
    private Boolean isVerified;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String email, String role, Boolean isActive, Boolean isVerified) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.isVerified = isVerified;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public void setIsVerified(Boolean verified) {
        isVerified = verified;
    }
}