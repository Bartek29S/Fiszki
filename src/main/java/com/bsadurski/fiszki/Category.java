package com.bsadurski.fiszki;


import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

public class Category implements IGetUserId {

    @NotEmpty(message = "nie moze byc puste")
    private String name;

    @Id
    private String id;

    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Category() {

    }
}
