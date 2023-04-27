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

    private String userId = "0";

    @NotEmpty(message = "nie moze byc puste")
    private String categoryId;

    private int learningStage;

    public int getLearningStage() {
        return learningStage;
    }

    public void setLearningStage(int learningStage) {
        this.learningStage = learningStage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

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
