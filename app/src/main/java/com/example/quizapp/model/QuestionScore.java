package com.example.quizapp.model;

public class QuestionScore {
    private String Question_Score;
    private String Player;
    private String Score;

    public QuestionScore() {

    }

    public QuestionScore(String question_Score, String player, String score) {
        Question_Score = question_Score;
        Player = player;
        Score = score;
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
}