package proiect.proiect_sociu_lucian;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proiect.proiect_sociu_lucian.model.Creator;
import proiect.proiect_sociu_lucian.model.VoteData;

public class CreateVoteController {
    @FXML
    private TextField candidateField;

    @FXML
    private TextField timeLimitField;

    @FXML
    private TextField maxUsersField;

    @FXML
    private Button addCandidateButton;

    @FXML
    private VBox candidateListLabel;

    @FXML
    private Button createVoteButton;

    @FXML
    private TextArea voteCodeLabel;
    
    @FXML
    private Button home;

    @FXML
    private TextField topic;

    private int ultimulId;
    public List<String> candidates;
    private ObjectMapper objectMapper;
    public String timeLimit;
    public int maxUsers;
    public String topicText;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JavaProject";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "lucians2";


    public CreateVoteController() {
        candidates = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }
    @FXML
    private void initialize() {

        addCandidateButton.setOnAction(event -> {
            String candidate = candidateField.getText();
            if (candidate.length() >= 3) {
                candidates.add(candidate);
                addLabel(candidate);
                candidateField.clear();
                System.out.println("Candidate added: " + candidate);
                showAlert("Info", "Field-ul " + candidate + " a fost adaugat cu succes in lista de vot!");
            } else {
                showAlert("Error", "Field-ul introdus trebuie sa contina cel putin 3 caractere!");
            }
        });

        createVoteButton.setOnAction(event -> {
            if (timeLimitField.getText().isEmpty()) {
                showAlert("Error", "Casuta pentru limita de timp este obligatorie!");
            } else if (timeLimitField.getText().length() > 0) {
                timeLimit = timeLimitField.getText();
            }

            if (maxUsersField.getText().isEmpty()) {
                showAlert("Error", "Casuta pentru numar maxim de useri ce au acces la vot este obligatorie!");
            } else if (maxUsersField.getText().length() > 0) {
                maxUsers = Integer.parseInt(maxUsersField.getText());
            }
            if (topic.getText().isEmpty()) {
                showAlert("Error", "Casuta pentru topic este obligatorie!");
            } else if (topic.getText().length() > 0) {
                topicText = topic.getText();
            }
            if (candidates.isEmpty()) {
                showAlert("Error", "Te rugam sa introduci cel putin un field in poll-ul de vot!");
            } else if (timeLimit.isEmpty()) {
                showAlert("Error", "Casuta pentru limita de timp este obligatorie!");
            } else if (maxUsers == 0) {
                showAlert("Error", "Casuta pentru numar maxim de useri ce au acces la vot este obligatorie!");
            } else if (topic.getText().isEmpty()) {
                showAlert("Error", "Casuta pentru topic este obligatorie!");
            }
             else {
                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                VoteData voteData = new VoteData(candidates, timeLimit, maxUsers, timestamp, topicText);
                    String voteCode = generateVoteCode();
                    voteData.setVoteCode(voteCode);
                  voteCodeLabel.setText(voteCode);
                    //objectMapper.writeValue(new File("src/main/resources/vote_data.json"), voteData);
                    //System.out.println("Vote data saved successfully.");
                    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                        String sql = "INSERT INTO voturi (cod_unic, timp_limita, nr_maxim_useri, topic,creator) VALUES (?, ?, ?, ?,?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, voteCode);
                        statement.setString(2, timeLimit);
                        statement.setInt(3, maxUsers);
                        statement.setString(4,topicText);
                        statement.setString(5, Creator.creator);
                        statement.executeUpdate();

                        Statement stmt = connection.createStatement();
                        String query = "SELECT id FROM voturi ORDER BY id DESC LIMIT 1";
                        ResultSet resultSet = stmt.executeQuery(query);
                        if (resultSet.next()) {
                             ultimulId = resultSet.getInt("id");
                        } else {
                            System.out.println("Tabela voturi este goala.");
                        }
                        resultSet.close();
                        for (String candidate : candidates) {
                            String insertQuery = "INSERT INTO candidati (id_vot ,cod_unic, nume_candidat,nr_voturi) VALUES (?, ?, ?,?)";

                            try (PreparedStatement statement1 = connection.prepareStatement(insertQuery)) {
                                statement1.setInt(1, ultimulId);
                                statement1.setString(2, voteCode);
                                statement1.setString(3, candidate);
                                statement1.setInt(4,0);
                                statement1.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

            }

        });
        home.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("WelcomeController.fxml"));
                Scene scene = new Scene(root, 500, 500);
                Stage stage = new Stage();
                stage.setTitle("Welcome!");
                stage.setScene(scene);
                stage.show();
                createVoteButton.getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void addLabel(String candidate) {
        Label label = new Label(candidate);
        candidateListLabel.getChildren().add(label);
    }

    private String generateVoteCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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
}

