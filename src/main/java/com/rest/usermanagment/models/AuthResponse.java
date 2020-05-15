package com.rest.usermanagment.models;

/**
 * Token class used to send back token to client
 */
public class AuthResponse {
    private String token;
    public AuthResponse(){

    }
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
