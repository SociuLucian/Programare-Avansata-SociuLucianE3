package proiect.proiect_sociu_lucian;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {
    @FXML
    private Button createVoteButton;

    @FXML
    private Button voteButton;

    @FXML
    private Button yourPolls;

    @FXML
    private void initialize(){
        createVoteButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("CreateVoteController.fxml"));
                Scene scene = new Scene(root, 500, 500);
                Stage stage = new Stage();
                stage.setTitle("Create Vote");
                stage.setScene(scene);
                stage.show();
                createVoteButton.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        voteButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("CodeCheck.fxml"));
                Scene scene = new Scene(root, 500, 200);
                Stage stage = new Stage();
                stage.setTitle("Verifica Codul");
                stage.setScene(scene);
                stage.show();
                voteButton.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        yourPolls.setOnAction(event-> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("YourPolls.fxml"));
                Scene scene = new Scene(root, 500, 200);
                Stage stage = new Stage();
                stage.setTitle("Poll-urile tale!");
                stage.setScene(scene);
                stage.show();
                yourPolls.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
