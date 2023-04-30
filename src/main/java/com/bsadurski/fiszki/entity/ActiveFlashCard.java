package com.bsadurski.fiszki.entity;

import org.springframework.data.annotation.Id;

public class ActiveFlashCard {

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

    @Id
    private int id;

    private int userId;

    public int getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    private int flashcardId;
}
