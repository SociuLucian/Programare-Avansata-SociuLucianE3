package proiect.proiect_sociu_lucian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proiect.proiect_sociu_lucian.model.VoteCode;

import java.sql.*;

public class VoteController {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JavaProject";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "lucians2";
    @FXML
    private Label topic;

    @FXML
    private Button voteButton;

    @FXML
    private ToggleGroup optionsToggleGroup;

    @FXML
    private VBox optionsContainer;

    private String code;

    public void setCode(String code) {
        this.code = code;
    }
    public void initialize() {
        try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD))
        {
            PreparedStatement statement = connection.prepareStatement("SELECT topic from voturi where cod_unic = ?");
            statement.setString(1,VoteCode.code);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                String buffer= resultSet.getString("topic");
                topic.setText(buffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        optionsToggleGroup = new ToggleGroup();
        populateOptionsFromDatabase();
        voteButton.setOnAction(event-> {
            RadioButton selectedOption = (RadioButton) optionsToggleGroup.getSelectedToggle();
        if (selectedOption != null) {
            String selectedOptionText = selectedOption.getText();
            showVoteConfirmation(selectedOptionText);
            try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement("UPDATE candidati set nr_voturi=nr_voturi+1 where nume_candidat = ? and cod_unic = ?")){
                statement.setString(1,selectedOptionText);
                statement.setString(2,VoteCode.code);
               // System.out.println(code);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(getClass().getResource("WelcomeController.fxml"));
                Scene scene = new Scene(root, 500, 500);
                Stage stage = new Stage();
                stage.setTitle("Welcome!");
                stage.setScene(scene);
                stage.show();
                voteButton.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error", "Va rugam selectati cel putin o casuta!");
        }
    });
    }

    private void showVoteConfirmation(String selectedOption) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vot confirmat");
        alert.setHeaderText(null);
        alert.setContentText("Ati votat pentru: " + selectedOption);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        if (message == "Error") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    private void populateOptionsFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(nume_candidat) from candidati where cod_unic = ?")) {
            statement.setString(1,VoteCode.code);
            //System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String candidate = resultSet.getString("nume_candidat");
                System.out.println(candidate);
                RadioButton radioButton = new RadioButton(candidate);
                radioButton.setToggleGroup(optionsToggleGroup);
                optionsContainer.getChildren().add(radioButton);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

