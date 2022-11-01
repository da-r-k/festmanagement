package com.example.fms.festmanagement.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDetails {

    @NotBlank
    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid Email")
    private String emailId;
    private String password;
    private String role;
    private boolean enabled;

    public String getemailId() {
        return this.emailId;
    }

    public void setemailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "{" +
            " emailId='" + getemailId() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            ", enabled='" + isEnabled() + "'" +
            "}";
    }

}
