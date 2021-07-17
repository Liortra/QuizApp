package com.example.quizapp.model;

public class QuestionScore {
    private String questionScore;
    private String player;
    private String score;
    private String categoryId;
    private String categoryName;

    public QuestionScore() {

    }

    public QuestionScore(String questionScore, String player, String score, String categoryId, String categoryName) {
        this.questionScore = questionScore;
        this.player = player;
        this.score = score;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(String questionScore) {
        this.questionScore = questionScore;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

