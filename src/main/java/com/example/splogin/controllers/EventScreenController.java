package com.example.splogin.controllers;

import com.example.splogin.models.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class EventScreenController implements Initializable {

    @FXML
    Button add,update, delete;

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> titleCol;

    @FXML
    private TableColumn<Event, Integer> idCol;

    @FXML
    private TableColumn<Event, String> descriptionCol;

    @FXML
    private TableColumn<Event, String> dateCol;

    @FXML
    private TableColumn<Event, String> locationCol;

    @FXML
    private TableColumn<Event, String> pictureCol;

    @FXML
    private TableColumn<Event, String> categoryCol;

    @FXML
    private TableColumn<Event, String> hostCol;

    @FXML
    private TableColumn<Event, String> authorCol;

    Parent root;
    private Connection connect;
    private PreparedStatement statement;
    private Statement statement2;
    private ResultSet resultSet;

    @FXML
    public void addEvent(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/com/example/splogin/views/AddEvent.fxml"));
        Stage window = (Stage) add.getScene().getWindow();
        window.setScene(new Scene(root, 918,522));

    }

    @FXML
    public void deleteEvent(ActionEvent event) throws Exception{


    }

    @FXML
    public void editEvent(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/com/example/splogin/views/UpdateEvent.fxml"));
        Stage window = (Stage) update.getScene().getWindow();
        window.setScene(new Scene(root, 918,522));

    }
    public ObservableList<Event> eventList() {

         connect = DatabaseConnection.getConnection();
        ObservableList<Event> eventList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM event";
            statement = connect.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Event eventData = new Event(
                        resultSet.getString("Title"),
                        resultSet.getInt("EventID"),
                        resultSet.getString("Date"),
                        resultSet.getString("Location"),
                        resultSet.getString("Image"),
                        resultSet.getString("Category"),
                        resultSet.getString("Host"),
                        resultSet.getString("Author"),
                        resultSet.getString("Description")
                );
                eventList.add(eventData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }

        return eventList;
    }

    public void showEventData() {
        ObservableList<Event> showEventList = eventList();

        idCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        pictureCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        hostCol.setCellValueFactory(new PropertyValueFactory<>("host"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        eventTable.setItems(showEventList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEventData();
    }
}
