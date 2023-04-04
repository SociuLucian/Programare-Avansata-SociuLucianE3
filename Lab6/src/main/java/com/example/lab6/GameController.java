package com.example.lab6;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Random;


public class GameController {
    private String savePath ="C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab6\\src\\main\\resources\\GameSaves";

    public static int countFilesInCurrentDirectory(File directory) {
        int count = 0;
        for (File file: directory.listFiles()) {
            if (file.isFile()) {
                count++;
            }
        }
        return count;
    }
    public void load(String path, Canvas canvas, GraphicsContext graphicsContext) {
        File directory = new File(path);
        int saves = countFilesInCurrentDirectory(directory);
        Stage stage1 = new Stage();
        Label label = new Label("Select known languages:");
        BorderPane pane = new BorderPane();
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14);
        label.setFont(font);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().add(label);
        pane.setCenter(vBox);
        Scene loadPanel = new Scene(pane,400,400);
        StringBuffer buffer = new StringBuffer();
        String imagePath = null;
        for (int i = 0; i < saves; i++) {
            CheckBox checkBox = new CheckBox("save" + i);
            checkBox.setFont(font);
            vBox.getChildren().add(checkBox);
            checkBox.setIndeterminate(true);
            checkBox.setOnAction(actionEvent -> {
                if(checkBox.isSelected())
                {
                    try {
                        FileInputStream input = new FileInputStream(path + "\\" + checkBox.getText() + ".png");
                        Image image = new Image(input);
                        graphicsContext.drawImage(image,0,0);
                        stage1.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        stage1.setTitle("Load");
        stage1.setScene(loadPanel);
        stage1.show();
    }

    public void save(Canvas canvas) throws IOException {
        Image image = canvas.snapshot(null,null);
        File file1 = new File(savePath);
        String filename = "save" + (countFilesInCurrentDirectory(file1)) + ".png";
        File file = new File(savePath + "\\" + filename);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
    public void drawBoard(GraphicsContext gcBuffer, Canvas canvas , int numOfDots , int numOfLines) {
        gcBuffer.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gcBuffer.setFill(Color.BLACK);
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
            gcBuffer.fillOval(x - 5, y - 5, 10, 10);
        }
        gcBuffer.setStroke(Color.GRAY);
        List<Pair<Integer, Integer>> drawnLines = new ArrayList<>();
        Random random = new Random();
        int i = 0;
        while (i < numOfLines) {
            int dot1 = random.nextInt(numOfDots);
            int dot2 = random.nextInt(numOfDots);
            Pair<Integer, Integer> line = new Pair<>(Math.min(dot1, dot2), Math.max(dot1, dot2));
            if (dot1 != dot2 && !drawnLines.contains(line)) {
                Point2D start = coordinates.get(dot1);
                Point2D end = coordinates.get(dot2);
                gcBuffer.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
                drawnLines.add(line);
                i++;
            }
        }
    }

}