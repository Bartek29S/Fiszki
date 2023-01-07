package com.bsadurski.fiszki.entity;


import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component()
public class User {

    @NotEmpty(message = "nie moze byc puste")
    private String name;

    @NotEmpty(message = "nie moze byc puste")
    private String surname;

    @NotEmpty(message = "nie moze byc puste")
    private String email;

    String password;

    String role;

    @Id
    private String id;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setSurname(String s) {
        this.surname = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {

    }
}
