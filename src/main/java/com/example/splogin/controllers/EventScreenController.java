package com.example.splogin.controllers;

import com.example.splogin.models.Event;
import com.example.splogin.utils.PageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.w3c.dom.events.MouseEvent;


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
//        root = FXMLLoader.load(getClass().getResource("/com/example/splogin/views/AddEvent.fxml"));
//        Stage window = (Stage) add.getScene().getWindow();
//        window.setScene(new Scene(root, 918,655));
        PageUtils pageUtils = new PageUtils();
        pageUtils.loadPage("/com/example/splogin/views/AddEvent.fxml");



    }

    @FXML
    public void deleteEvent(ActionEvent event) throws Exception{

        try{
            connect = DatabaseConnection.getConnection();
            ObservableList<Event> selectedEvents = eventTable.getSelectionModel().getSelectedItems();

            // Iterate through the selected events and delete them from the database
            for (Event selectedEvent : selectedEvents) {
                int eventID = selectedEvent.getEventID();

                if (deleteEventFromDatabase(eventID)) {
                    eventTable.getItems().remove(selectedEvent);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private boolean deleteEventFromDatabase(int eventID) {
        try {
            String sql = "DELETE FROM event WHERE EventID = ?";
            PreparedStatement deleteStatement = connect.prepareStatement(sql);
            deleteStatement.setInt(1, eventID);

            int rowsAffected = deleteStatement.executeUpdate();
            return rowsAffected > 0; // Return true if rows were deleted, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Deletion failed
        }
    }

    @FXML
    public void editEvent(ActionEvent event) throws Exception {
//        root = FXMLLoader.load(getClass().getResource("/com/example/splogin/views/UpdateEvent.fxml"));
//        Stage window = (Stage) update.getScene().getWindow();
//        window.setScene(new Scene(root, 918,655));
        PageUtils pageUtils = new PageUtils();
        pageUtils.loadPage("/com/example/splogin/views/UpdateEvent.fxml");

    }
    public ObservableList<Event> eventList() {

         connect = DatabaseConnection.getConnection();
        ObservableList<Event> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM event";
            statement = connect.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Event eventData;

            while (resultSet.next()) {

                 eventData = new Event(
                         resultSet.getString("Title"),
                         resultSet.getInt("EventID"),
                         resultSet.getString("Date"),
                         resultSet.getString("location"),
                         resultSet.getString("Image"),
                         resultSet.getString("Category"),
                         resultSet.getString("Host"),
                         resultSet.getString("Author"),
                         resultSet.getString("Description")
                );
                list.add(eventData);
                //System.out.println(resultSet.getInt("eventID"));
            }


            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            DatabaseConnection.closeConnection();
//        }


        return list;
    }

    public void showEventData() {
        ObservableList<Event> showEventList = eventList();


        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        pictureCol.setCellValueFactory(new PropertyValueFactory<>("Image"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        hostCol.setCellValueFactory(new PropertyValueFactory<>("host"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));



        eventTable.setItems(showEventList);
    }

    public void selectData() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEventData();
    }
}
