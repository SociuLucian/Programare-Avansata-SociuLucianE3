package com.javalab.lab6.model;

import javafx.scene.paint.Color;

public class Player {
    private String playerName;
    private PlayerColor playerColor;

    public Player() {

    }

    public Player(String playerName, PlayerColor playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "Player{" + "playerName='" + playerName + '\'' + '}';
    }

    public Color getColor() {
        return playerColor == PlayerColor.RED ? Color.RED : Color.BLUE;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }
}
