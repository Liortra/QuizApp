package com.example.quizapp.model;

public class Ranking {
    private String playerName;
    private long score;

    public Ranking() {
    }

    public Ranking(String playerName, long score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
