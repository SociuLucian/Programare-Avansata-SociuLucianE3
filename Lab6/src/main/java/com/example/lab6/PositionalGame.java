package com.example.lab6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class PositionalGame extends Application {
    private Canvas canvas;
    private GraphicsContext gc;
    private int numOfDots = 6;
    private int numOfLines = 4;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PositionalGame.class.getResource("hello-view.fxml"));
        GameController gameController = new GameController();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 700);
        Canvas buffer = new Canvas(800, 600);
        GraphicsContext gcBuffer = buffer.getGraphicsContext2D();
        //Label configLabel = new Label("Configuration");
        Label dotsLabel = new Label("Number of Dots:");
        TextField dotsField = new TextField(Integer.toString(numOfDots));
        Label linesLabel = new Label("Number of Lines:");
        TextField linesField = new TextField(Integer.toString(numOfLines));
        Button createBtn = new Button("Create Game");
        createBtn.setOnAction(actionEvent -> {
            //Canvas buffer = new Canvas(800,600);
            //GraphicsContext gcBuffer = buffer.getGraphicsContext2D();
            numOfDots = Integer.parseInt(dotsField.getText());
            numOfLines = Integer.parseInt(linesField.getText());
            gameController.drawBoard(gcBuffer, buffer,numOfDots,numOfLines);
            WritableImage image = buffer.snapshot(null, null);
            canvas = new Canvas(800, 600);
            gc = canvas.getGraphicsContext2D();
            gc.drawImage(image, 0, 0);
            root.setCenter(canvas);
        });
        HBox configBox = new HBox(10, dotsLabel, dotsField, linesLabel, linesField, createBtn);
        configBox.setAlignment(Pos.CENTER);
        configBox.setPadding(new Insets(10));
        configBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setTop(configBox);
        //Canvas buffer = new Canvas(800,600);
        //GraphicsContext gcBuffer = buffer.getGraphicsContext2D();
        WritableImage image = buffer.snapshot(null, null);
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);
        root.setCenter(canvas);
        Button loadBtn = new Button("Load");
        loadBtn.setOnAction(actionEvent -> {
            canvas = new Canvas(800, 600);
            gc = canvas.getGraphicsContext2D();
           gameController.load(gameController.getSavePath(),canvas,gc);
            root.setCenter(canvas);
        });
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(actionEvent -> {
            try {
                gameController.save(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(actionEvent -> stage.close());
        HBox controlBox = new HBox(10, loadBtn, saveBtn, exitBtn);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setPadding(new Insets(10));
        controlBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setBottom(controlBox);
        stage.setTitle("Positional Game");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}