package com.bsadurski.fiszki.entity;


import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

public class FlashCard {

    @NotEmpty(message = "nie moze byc puste")
    private String englishWord;

    @NotEmpty(message = "nie moze byc puste")
    private String polishWord;

    @Id
    private String id;

    private String userId;

    public String getEnglishWord() {
        return englishWord;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setEnglishWord(String s) {
        this.englishWord = s;
    }

    public void setPolishWord(String s) {
        this.polishWord = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlashCard() {

    }
}
