package com.example.quizapp.model;

public class QuestionScore {
    private String Question_Score;
    private String Player;
    private String Score;
    private String CategoryId;
    private String CategoryName;

    public QuestionScore() {

    }

    public QuestionScore(String question_Score, String player, String score, String categoryId, String categoryName) {
        Question_Score = question_Score;
        Player = player;
        Score = score;
        CategoryId = categoryId;
        CategoryName = categoryName;
    }

    public String getQuestion_Score() {
        return Question_Score;
    }

    public void setQuestion_Score(String question_Score) {
        Question_Score = question_Score;
    }

    public String getPlayer() {
        return Player;
    }

    public void setPlayer(String player) {
        Player = player;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}

