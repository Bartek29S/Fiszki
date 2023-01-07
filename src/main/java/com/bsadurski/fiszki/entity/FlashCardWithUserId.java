package com.bsadurski.fiszki.entity;

import javax.validation.constraints.NotEmpty;

public class  FlashCardWithUserId extends FlashCard implements IGetUserId {

    @NotEmpty(message = "nie moze byc puste")
    String userId;

    public FlashCardWithUserId() {
        super();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String id) {
        this.userId = id;
    }
}
