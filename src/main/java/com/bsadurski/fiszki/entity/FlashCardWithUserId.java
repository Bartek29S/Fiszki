package com.bsadurski.fiszki.entity;

import javax.validation.constraints.NotEmpty;

public class  FlashCardWithUserId extends FlashCard implements IGetUserId {

    @NotEmpty(message = "nie moze byc puste")
    int userId;

    public FlashCardWithUserId() {
        super();
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int id) {
        this.userId = id;
    }
}
