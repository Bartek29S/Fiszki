package com.bsadurski.fiszki.entity;


import org.springframework.stereotype.Component;

@Component
public class Authorisation {

    private int userId = 0;

    private String name = "Bartek";

    private String surname = "Mato";


    String getUserFullName() {
       return  this.name + this.surname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
