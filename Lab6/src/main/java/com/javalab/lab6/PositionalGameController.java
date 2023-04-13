package com.javalab.lab6;

import com.javalab.lab6.model.Game;
import com.javalab.lab6.model.Player;
import com.javalab.lab6.model.PlayerColor;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PositionalGameController {
    public ComboBox<Double> lineProbabilityCombo;
    public Spinner<Integer> numberOfDotsSpinner;
    public Canvas canvasPaneId;
    public TextArea redPlayerName;
    public TextArea bluePlayerName;

    public Group canvasGroupRoot;
    public AnchorPane mainDialog;
    public TextArea playerToMoveName;

    private Game game;

    @FXML
    public void initialize() {
        lineProbabilityCombo.getItems().removeAll(lineProbabilityCombo.getItems());
        lineProbabilityCombo.getItems().addAll(0.0, 0.15, 0.25, 0.35, 0.5, 0.65, 0.75, 1.0);
        lineProbabilityCombo.getSelectionModel().select(1.0);

        numberOfDotsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
    }

    public void LoadButton(MouseEvent mouseEvent) {
        ResetButton(mouseEvent);

        if (game == null) {
            game = new Game();
        }
        game.loadState();

        Integer dotsNumber = game.getNumberOfDots();
        Map<Line, Player> playerMoves = game.getPlayerMoves();
        drawDots(dotsNumber);

        game.getGreyLines().forEach(greyLine -> {
            greyLine.setStroke(Color.GREY);
            greyLine.setStrokeWidth(8);
            canvasGroupRoot.getChildren().add(greyLine);
            greyLine.addEventHandler(MouseEvent.MOUSE_PRESSED, getEventHandlers());
        });

        playerMoves.entrySet().forEach(entry -> {
            Player player = entry.getValue();
            Line line = entry.getKey();
            line.setStroke(player.getColor());
            line.setStrokeWidth(5);
            canvasGroupRoot.getChildren().add(line);

        });
        playerToMoveName.setText(game.getNextPlayerToMove().getPlayerName());
    }

    private List<Line> greyLines(Integer dotsNumber, List<Circle> dots) {
        List<Line> lines = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < dotsNumber; i++) {
            for (int j = i + 1; j < dotsNumber; j++) {
                if (random.nextDouble() <= lineProbabilityCombo.getValue()) {
                    Line line = new Line(dots.get(i).getCenterX(), dots.get(i).getCenterY(), dots.get(j).getCenterX(), dots.get(j).getCenterY());
                    line.setStroke(Color.GREY);
                    line.setStrokeWidth(8);
                    canvasGroupRoot.getChildren().add(line);
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    private List<Circle> drawDots(Integer dotsNumber) {
        double centerX = canvasPaneId.getWidth() / 2;
        double centerY = canvasPaneId.getHeight() / 1.7;


        List<Circle> dots = new ArrayList<>();

        for (int i = 0; i < dotsNumber; i++) {
            double angle = 2 * Math.PI * i / dotsNumber;
            int CIRCLE_RADIUS = 250;
            double x = centerX + CIRCLE_RADIUS * Math.cos(angle);
            double y = centerY + CIRCLE_RADIUS * Math.sin(angle);


            Circle dot = new Circle(x, y, 10, Color.BLACK);


            dots.add(dot);
            canvasGroupRoot.getChildren().add(dot);
        }

        return dots;
    }

    public void SaveButton(MouseEvent mouseEvent) throws IOException {
        game.saveState();
    }

    public void ResetButton(MouseEvent mouseEvent) {
        canvasGroupRoot.getChildren().clear();
        if (game != null) {
            game.resetGame();
        }
    }

    public void ExitButton(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void CreateGameButton(MouseEvent mouseEvent) {
        canvasGroupRoot.getChildren().clear();

        String redPlayer = redPlayerName.getText().trim();
        String bluePlayer = bluePlayerName.getText().trim();

        Player player1 = new Player(redPlayer, PlayerColor.RED);
        Player player2 = new Player(bluePlayer, PlayerColor.BLUE);

        game = new Game(player1, player2, numberOfDotsSpinner.getValue(), lineProbabilityCombo.getValue());

        playerToMoveName.setText(game.getFirstPlayerToMove().getPlayerName());

        GraphicsContext gc = canvasPaneId.getGraphicsContext2D();
        gc.setLineWidth(1.0);

        Integer dotsNumber = numberOfDotsSpinner.getValue();
        List<Circle> dots = drawDots(dotsNumber);

        List<Line> greyLines = greyLines(dotsNumber, dots);
        greyLines.forEach(greyLine -> greyLine.addEventHandler(MouseEvent.MOUSE_PRESSED, getEventHandlers()));

        game.setGreyLines(greyLines);
    }

    private EventHandler<MouseEvent> getEventHandlers() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Player playerToMove;

                if (game.isFirstMove()) {
                    playerToMove = game.getFirstPlayerToMove();
                } else {
                    playerToMove = game.getNextPlayerToMove();
                }

                Line line = (Line) mouseEvent.getSource();

                if (game.moveIsValid(line)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Line already colored, choose another line", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    System.out.println("Coloring line for player " + playerToMove.getPlayerName() + " with color = " + playerToMove.getColor());
                    line.setStroke(playerToMove.getColor());
                    game.addMove(line, playerToMove);
                    playerToMoveName.setText(game.getNextPlayerToMove().getPlayerName());

                    if (game.checkForWinner(playerToMove)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player " + playerToMove.getPlayerName() + " has won! ", ButtonType.OK);
                        alert.showAndWait();

                        canvasGroupRoot.getChildren().clear();
                    }
                }
            }
        };
    }

    public void ExportPNG(MouseEvent mouseEvent) {

        WritableImage writableImage = new WritableImage((int) canvasPaneId.getWidth(), (int) canvasPaneId.getHeight());
        this.canvasGroupRoot.snapshot(null, writableImage);
        File directory = new File("C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab6\\src\\main\\resources\\PngExports");
        String filename = "save" + (countFilesInCurrentDirectory(directory)) + ".png";

        File file = new File("C:\\Users\\LucianPC\\Documents\\GitHub\\Programare-Avansata\\Lab6\\src\\main\\resources\\PngExports\\" +filename);


        Platform.runLater(() -> {
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            try {
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static int countFilesInCurrentDirectory(File directory) {
        int count = 0;
        for (File file: directory.listFiles()) {
            if (file.isFile()) {
                count++;
            }
        }
        return count;
    }
}