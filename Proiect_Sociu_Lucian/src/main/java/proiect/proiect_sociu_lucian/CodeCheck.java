package proiect.proiect_sociu_lucian;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proiect.proiect_sociu_lucian.model.VoteCode;

import java.sql.*;

public class CodeCheck {

    @FXML
    private Label titleLabel;

    @FXML
    private Button checkCode;

    @FXML
    private TextField codeBox;

    @FXML
    private Button homeButton;

    private VoteController voteController;
    public String code;
    private int check = 0;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JavaProject";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "lucians2";

    @FXML
    private void initialize() {
        checkCode.setOnAction(event -> {
            if(codeBox.getText().isEmpty())
            {
                showAlert("Error" , "Va rugam sa introduceti un cod!");
            }
            else {
                code=codeBox.getText();
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
                    Statement stmt = connection.createStatement();
                    String query = "SELECT cod_unic from voturi";
                    ResultSet resultSet = stmt.executeQuery(query);
                    while (resultSet.next())
                    {
                       if(code.equals(resultSet.getString("cod_unic"))){
                           showAlert("Info " ,"Codul introdus este corect! \n Va trimitem catre fereastra de vot!");
                           check =1;
                           VoteCode.code=code;
                           try {
                               Parent root = FXMLLoader.load(getClass().getResource("VoteController.fxml"));
                               Scene scene = new Scene(root, 500, 500);
                               Stage stage = new Stage();
                               stage.setTitle("Pagina de Vot");
                               stage.setScene(scene);
                               stage.show();
                               checkCode.getScene().getWindow().hide();
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                           break;
                       }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(check==0)
            {
                showAlert("Error", "Codul introdus nu este corect!");
            }
        });
        homeButton.setOnAction(event->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("WelcomeController.fxml"));
                Scene scene = new Scene(root, 500, 500);
                Stage stage = new Stage();
                stage.setTitle("Welcome!");
                stage.setScene(scene);
                stage.show();
                homeButton.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
    public void setVoteController(VoteController voteController) {
        this.voteController = voteController;
    }
}

