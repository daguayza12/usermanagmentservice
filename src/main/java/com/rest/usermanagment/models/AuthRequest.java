package com.rest.usermanagment.models;

/**
 * Auth class used to authenticate user before creating token
 */
public class AuthRequest {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
}
