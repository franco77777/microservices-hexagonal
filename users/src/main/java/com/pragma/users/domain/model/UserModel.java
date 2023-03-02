package com.pragma.users.domain.model;

import com.pragma.users.infrastructure.output.utils.AuthorityName;

public class UserModel {
    private Long id;
    private String firstname;
    private String lastname;

    private String mobile;

    private String password;

    private String email;
    private AuthorityName roles;
    private String dni;



    public UserModel() {
    }

    public UserModel(Long id, String firstname, String lastname, String mobile, String password, String email, AuthorityName roles, String dni) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.dni = dni;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthorityName getRoles() {
        return roles;
    }

    public void setRoles(AuthorityName roles) {
        this.roles = roles;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
