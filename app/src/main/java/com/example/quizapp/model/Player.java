package com.example.quizapp.model;

public class Player {
    private String playerName;
    private String password;
    private String email;

    public Player() {
    }

    public Player(String playerName, String password, String email) {
        this.playerName = playerName;
        this.password = password;
        this.email = email;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
