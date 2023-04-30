package com.bsadurski.fiszki.entity;


import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

public class Category implements IGetUserId {

    @NotEmpty(message = "nie moze byc puste")
    private String name;

    @Id
    private int id;

    private int userId;

    public String getName() {
        return name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Category() {

    }
}
