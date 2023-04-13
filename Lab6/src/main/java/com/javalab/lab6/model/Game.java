package com.javalab.lab6.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Player nextPlayerToMove;
    private Player firstPlayerToMove;
    private Integer numberOfDots;
    private Double lineProbability;
    private transient final Map<Line, Player> playerMoves;
    private transient List<Line> greyLines;
    private final Map<String, List<SerializedLine>> playerMovesSerialized;
    private final List<SerializedLine> greyLinesSerialized;

    public Game() {
        this.playerMoves = new HashMap<>();
        this.playerMovesSerialized = new HashMap<>();
        this.greyLines = new ArrayList<>();
        this.greyLinesSerialized = new ArrayList<>();
    }

    public Map<Line, Player> getPlayerMoves() {
        return playerMoves;
    }

    public Double getLineProbability() {
        return lineProbability;
    }

    public void setLineProbability(Double lineProbability) {
        this.lineProbability = lineProbability;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Game(Player player1, Player player2, Integer dots, Double lineProbability) {
        this();
        this.playerOne = player1;
        this.playerTwo = player2;
        this.numberOfDots = dots;
        this.lineProbability = lineProbability;

        firstPlayerToMove = player1.getColor() == Color.RED ? player1 : player2;
        nextPlayerToMove = player1.getColor() == Color.BLUE ? player1 : player2;

    }

    public Player getFirstPlayerToMove() {
        return firstPlayerToMove;
    }

    public void addMove(Line line, Player currentPlayer) {
        if (playerOne.getPlayerName().equals(currentPlayer.getPlayerName())) {
            nextPlayerToMove = playerTwo;
        } else if (playerTwo.getPlayerName().equals(currentPlayer.getPlayerName())) {
            nextPlayerToMove = playerOne;
        }

        this.playerMoves.put(line, currentPlayer);
    }

    public Player getNextPlayerToMove() {
        return nextPlayerToMove;
    }


    @Override
    public String toString() {
        return "Game{" + "playerOne=" + playerOne + ", playerTwo=" + playerTwo + ", nextPlayerToMove=" + nextPlayerToMove + ", firstPlayerToMove=" + firstPlayerToMove + '}';
    }

    public boolean isFirstMove() {
        return this.playerMoves.size() == 0;
    }

    public boolean moveIsValid(Line line) {
        return playerMoves.containsKey(line);
    }

    public boolean checkForWinner(Player currentPlayer) {
        List<Line> currentPlayerMoves = new ArrayList<>();
        playerMoves.forEach((key, value) -> {
            if (value == currentPlayer) {
                currentPlayerMoves.add(key);
            }
        });

        if (currentPlayerMoves.size() < 3) {
            return false;
        }
        boolean triangleFound = hasTriangle(currentPlayerMoves);
        if (triangleFound) {
            resetGame();
        }

        return triangleFound;
    }

    public Integer getNumberOfDots() {
        return numberOfDots;
    }

    public void setNumberOfDots(Integer numberOfDots) {
        this.numberOfDots = numberOfDots;
    }

    public void resetGame() {
        this.playerMoves.clear();
        this.playerTwo = null;
        this.playerOne = null;
        this.nextPlayerToMove = null;
        this.firstPlayerToMove = null;
        this.playerMovesSerialized.clear();
    }

    private boolean hasTriangle(List<Line> lines) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                for (int k = j + 1; k < lines.size(); k++) {
                    Line line1 = lines.get(i);
                    Line line2 = lines.get(j);
                    Line line3 = lines.get(k);

                    if (linesIntersect(line1, line2) && linesIntersect(line1, line3) && linesIntersect(line2, line3)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean linesIntersect(Line line1, Line line2) {
        double x1 = line1.getStartX();
        double y1 = line1.getStartY();
        double x2 = line1.getEndX();
        double y2 = line1.getEndY();
        double x3 = line2.getStartX();
        double y3 = line2.getStartY();
        double x4 = line2.getEndX();
        double y4 = line2.getEndY();

        double dx12 = x2 - x1;
        double dy12 = y2 - y1;
        double dx34 = x4 - x3;
        double dy34 = y4 - y3;

        double denominator = (dy12 * dx34 - dx12 * dy34);

        if (denominator == 0) {
            return false;
        }

        double t1 = ((x1 - x3) * dy34 + (y3 - y1) * dx34) / denominator;
        if (t1 < 0 || t1 > 1) {
            return false;
        }

        double t2 = ((x3 - x1) * dy12 + (y1 - y3) * dx12) / -denominator;
        if (t2 < 0 || t2 > 1) {
            return false;
        }

        return true;
    }

    public void saveState() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.playerMoves.forEach((key, value) -> {
            List<SerializedLine> serializedLines = playerMovesSerialized.get(value.getPlayerName());
            if (serializedLines == null) {
                serializedLines = new ArrayList<>();
                playerMovesSerialized.put(value.getPlayerName(), serializedLines);
            }
            serializedLines.add(SerializedLine.fromLine(key));
        });

        this.getGreyLines().forEach(line -> this.greyLinesSerialized.add(SerializedLine.fromLine(line)));

        String gameAsJson = gson.toJson(this);
        try (PrintWriter out = new PrintWriter(new FileWriter("game-state.json"))) {
            out.write(gameAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            playerMovesSerialized.clear();
            greyLinesSerialized.clear();
        }
    }

    public void setNextPlayerToMove(Player nextPlayerToMove) {
        this.nextPlayerToMove = nextPlayerToMove;
    }

    public void setFirstPlayerToMove(Player firstPlayerToMove) {
        this.firstPlayerToMove = firstPlayerToMove;
    }

    public Map<String, List<SerializedLine>> getPlayerMovesSerialized() {
        return playerMovesSerialized;
    }

    public void loadState() {
        greyLines.clear();
        playerMoves.clear();
        
        Gson gson = new Gson();

        FileReader fileReader;
        try {
            fileReader = new FileReader("game-state.json");

            Game gameDeserialized = gson.fromJson(fileReader, Game.class);
            this.playerOne = gameDeserialized.getPlayerOne();
            this.playerTwo = gameDeserialized.getPlayerTwo();
            this.firstPlayerToMove = gameDeserialized.getFirstPlayerToMove();
            this.nextPlayerToMove = gameDeserialized.getNextPlayerToMove();
            this.numberOfDots = gameDeserialized.getNumberOfDots();

            Map<String, List<SerializedLine>> playerMovesSerialized = gameDeserialized.getPlayerMovesSerialized();

            playerMovesSerialized.entrySet().forEach(gameDeserializedEntry -> {
                Player player = gameDeserializedEntry.getKey().equals(playerOne.getPlayerName()) ? playerOne : playerTwo;
                List<SerializedLine> serializedLines = gameDeserializedEntry.getValue();
                serializedLines.stream().forEach(serializedLine -> {
                    Line line = serializedLine.toLine();
                    this.playerMoves.put(line, player);
                });
            });

            List<SerializedLine> serializedLines = gameDeserialized.getGreyLinesSerialized();
            serializedLines.forEach(line -> {
                greyLines.add(line.toLine());
            });

            gameDeserialized.getPlayerMovesSerialized().clear();
            gameDeserialized.getGreyLinesSerialized().clear();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Line> getGreyLines() {
        return greyLines;
    }

    public void setGreyLines(List<Line> greyLines) {
        this.greyLines = greyLines;
    }

    public List<SerializedLine> getGreyLinesSerialized() {
        return greyLinesSerialized;
    }
}
