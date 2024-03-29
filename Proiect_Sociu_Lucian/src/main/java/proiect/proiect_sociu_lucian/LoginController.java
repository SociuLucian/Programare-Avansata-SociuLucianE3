package proiect.proiect_sociu_lucian;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;
import proiect.proiect_sociu_lucian.model.AuthData;
import proiect.proiect_sociu_lucian.model.Creator;

public class LoginController {
    public File myFile = new File("src/main/resources/authData.json");
    private List<AuthData> encryptedUsers= new ArrayList<>();
    public Button createAccountButton;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Verificare utilizator și parolă
            try {
                if (checkCredentials(username, hashPassword(password))) {
                    Creator.creator=username;
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("WelcomeController.fxml"));
                        Scene scene = new Scene(root, 500, 500);
                        Stage stage = new Stage();
                        stage.setTitle("Welcome!");
                        stage.setScene(scene);
                        stage.show();
                        loginButton.getScene().getWindow().hide();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    showAlert("Autentificare eșuată", "Verifică utilizatorul și parola.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Eroare", "Eroare la citirea fișierului de autentificare.");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                showAlert("Eroare", "Eroare la criptarea parolei.");
            }
        });
        createAccountButton.setOnAction(actionEvent -> {
            showCreateAccountPage();
        });
    }

    private boolean checkCredentials(String username, String hashedPassword) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        encryptedUsers = objectMapper.readValue(myFile, new TypeReference<List<AuthData>>() {});
        AuthData authData = new AuthData(username,hashedPassword);
        System.out.println(hashedPassword);
        return encryptedUsers.contains(authData);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showCreateAccountPage()  {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("CreateAccountController.fxml"));
            Scene scene = new Scene(root, 500, 500);
            Stage stage = new Stage();
            stage.setTitle("Creare Cont Nou");
            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}