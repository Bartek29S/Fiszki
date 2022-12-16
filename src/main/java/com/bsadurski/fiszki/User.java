package com.bsadurski.fiszki;


import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

public class User {

    @NotEmpty(message = "nie moze byc puste")
    private String name;

    @NotEmpty(message = "nie moze byc puste")
    private String surname;

    @NotEmpty(message = "nie moze byc puste")
    private String email;

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

    public User() {

    }
}
