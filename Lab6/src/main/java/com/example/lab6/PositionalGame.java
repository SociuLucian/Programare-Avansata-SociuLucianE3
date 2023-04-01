package com.example.lab6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.crypto.Cipher;
import java.io.IOException;
import java.util.*;

public class PositionalGame extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private int numOfDots = 6;
    private int numOfLines = 4;
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PositionalGame.class.getResource("hello-view.fxml"));
        BorderPane root = new BorderPane();
        Scene  scene= new Scene(root, 900, 700);
        Label configLabel = new Label("Configuration");
        Label dotsLabel = new Label("Number of Dots:");
        TextField dotsField = new TextField(Integer.toString(numOfDots));
        Label linesLabel = new Label("Number of Lines:");
        TextField linesField = new TextField(Integer.toString(numOfLines));
        Button createBtn = new Button("Create Game");
        createBtn.setOnAction(actionEvent -> {
             numOfDots = Integer.parseInt(dotsField.getText());
             numOfLines = Integer.parseInt(linesField.getText());
            drawBoard();
        });
        HBox configBox = new HBox(10, dotsLabel, dotsField, linesLabel, linesField, createBtn);
        configBox.setAlignment(Pos.CENTER);
        configBox.setPadding(new Insets(10));
        configBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setTop(configBox);
         canvas = new Canvas(800, 600);
         gc = canvas.getGraphicsContext2D();
        drawBoard();
        root.setCenter(canvas);

        Button loadBtn = new Button("Load");
        Button saveBtn = new Button("Save");
        Button exitBtn = new Button("Exit");
        HBox controlBox = new HBox(10, loadBtn, saveBtn, exitBtn);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setBottom(controlBox);
        stage.setTitle("Positional Game");
        stage.setScene(scene);
        stage.show();
    }
    private void drawBoard() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;
        double radius = Math.min(centerX, centerY) * 0.9;
        double angleIncrement = 2 * Math.PI / numOfDots;
        List<Point2D> coordinates = new ArrayList<>();
        for (int i = 0; i < numOfDots; i++) {
            double angle = i * angleIncrement;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            coordinates.add(new Point2D(x, y));
            gc.fillOval(x - 5, y - 5, 10, 10);
        }
        gc.setStroke(Color.GRAY);
        Set<Pair<Integer, Integer>> drawnLines = new HashSet<>();
        Random random = new Random();
        int i = 0;
        while(i<numOfLines){
            int dot1 = random.nextInt(numOfDots);
            int dot2 = random.nextInt(numOfDots);
            Pair<Integer, Integer> line = new Pair<>(Math.min(dot1, dot2), Math.max(dot1, dot2));
            if (dot1 != dot2 && !drawnLines.contains(line)) {
                Point2D start = coordinates.get(dot1);
                Point2D end = coordinates.get(dot2);
                gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
                drawnLines.add(line);
                i++;
            }
        }
    }
    public static void main(String[] args) {
        launch();
    }
}