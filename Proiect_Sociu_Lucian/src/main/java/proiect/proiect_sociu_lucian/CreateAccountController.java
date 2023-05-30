package proiect.proiect_sociu_lucian;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import proiect.proiect_sociu_lucian.model.AuthData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateAccountController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createButton;

    @FXML
    private void initialize() {
        createButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                if (validateInput(username, password)) {
                    String hashedPassword = hashPassword(password);
                    saveAccountData(username, hashedPassword);
                    showAlert("Cont creat", "Contul a fost creat cu succes!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Eroare", "Eroare la salvarea datelor contului.");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                showAlert("Eroare", "Eroare la criptarea parolei.");
            }
        });
    }

    private boolean validateInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Eroare", "Numele de utilizator și parola trebuie completate.");
            return false;
        } else if (username.length() < 8 || password.length() < 8) {
            showAlert("Eroare", "Numele de utilizator și parola trebuie să fie de cel puțin 8 caractere.");
            return false;
        }
        return true;
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

    private void saveAccountData(String username, String hashedPassword) throws IOException {
        AuthData authData = new AuthData(username, hashedPassword);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("authData.json"), authData);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
