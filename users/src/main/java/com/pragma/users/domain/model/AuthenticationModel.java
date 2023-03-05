package com.pragma.users.domain.model;
public class AuthenticationModel {

    private String email;
    private String password;

    public AuthenticationModel() {
    }

    public AuthenticationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

