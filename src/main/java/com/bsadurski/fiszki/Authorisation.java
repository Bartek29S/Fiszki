package com.bsadurski.fiszki;


import org.springframework.stereotype.Component;

@Component
public class Authorisation {

    private String userId = "1";

    private String name = "Bartek";

    private String surname = "Mato";

    String getUserFullName() {
       return  this.name + this.surname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
