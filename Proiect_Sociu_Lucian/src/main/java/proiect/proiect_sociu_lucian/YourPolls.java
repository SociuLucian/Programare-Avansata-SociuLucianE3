package proiect.proiect_sociu_lucian;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.ranges.DocumentRange;
import proiect.proiect_sociu_lucian.model.Creator;
import proiect.proiect_sociu_lucian.model.VoteCode;
import proiect.proiect_sociu_lucian.model.VoteView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class YourPolls {
    @FXML
    private TableView<VoteView> dataTable;

    @FXML
    private TableColumn<VoteView, String> topic;

    @FXML
    private TableColumn<VoteView, Integer> nrVoturi;

    @FXML
    private TableColumn<VoteView,String> winner;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<VoteView,Integer> nrTotalVoturi;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JavaProject";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "lucians2";

    @FXML
    private void initialize()
    {
        topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        nrVoturi.setCellValueFactory(new PropertyValueFactory<>("nrVoturi"));
        nrTotalVoturi.setCellValueFactory(new PropertyValueFactory<>("nrTotalVoturi"));
        winner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        String query = "SELECT c.nume_candidat, v.topic, nr_voturi ,sum(nr_voturi) as total_voturi  FROM candidati c JOIN voturi v ON c.id_vot = v.id  where c.cod_unic=v.cod_unic and creator = ? " +
                "                GROUP BY c.nume_candidat, v.topic, nr_voturi,c.cod_unic ORDER BY nr_voturi DESC limit 1";
        List<VoteView> voteViews = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, Creator.creator);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                VoteView voteView = new VoteView(
                        resultSet.getString("topic"),
                        resultSet.getString("nume_candidat"),
                        resultSet.getInt("nr_voturi"),
                        resultSet.getInt("total_voturi")
                );
                System.out.println(voteView);
                voteViews.add(voteView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<VoteView> data = FXCollections.observableArrayList();
        data.addAll(voteViews);
        dataTable.getColumns().add(topic);
        dataTable.getColumns().add(nrVoturi);
        dataTable.getColumns().add(nrTotalVoturi);
        dataTable.getColumns().add(winner);
        dataTable.setItems(data);
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
}
