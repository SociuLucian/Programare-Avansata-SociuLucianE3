package proiect.proiect_sociu_lucian;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        primaryStage.setTitle("Pagină de Autentificare");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}