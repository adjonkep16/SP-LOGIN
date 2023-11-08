package com.example.splogin.controllers;

import com.example.splogin.models.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class AddEventController implements Initializable {

    @FXML
    private TextField eventID;

    @FXML
    private TextField description;

    @FXML
    private TextField title;

    @FXML
    private TextField date;

    @FXML
    private TextField location;

    @FXML
    private TextField host;

    @FXML
    private TextField category;

    @FXML
    private TextField author;

    @FXML
    private ImageView EventPic;

    @FXML
    private Button add;

    @FXML
    private Button picture;

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

    @FXML
    private AnchorPane apane;

    @FXML
    private Label fpath;

    Parent root;
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @FXML
    public void AddButton(ActionEvent event) throws Exception {

        connect = DatabaseConnection.getConnection();
        String sql = "INSERT INTO event VALUES (?,?,?,?,?,?,?,?,?)";

        try{

            if(eventID.getText().isEmpty() | title.getText().isEmpty()
                    | date.getText().isEmpty()
                    | description.getText().isEmpty()
                    | category.getText().isEmpty()
                    | author.getText().isEmpty()
                    | host.getText().isEmpty()
                    | location.getText().isEmpty()
                    | EventPic.getImage() == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            }else{

                statement = connect.prepareStatement(sql);
                statement.setString(1, eventID.getText());
                statement.setString(2, title.getText());
                statement.setString(3, description.getText());
                statement.setString(4, location.getText());
                statement.setString(5, date.getText());
                statement.setString(6, author.getText());
                statement.setString(7, host.getText());
                statement.setString(8, category.getText());
                statement.setString(9, String.valueOf(EventPic.getImage()));

                statement.executeUpdate();

                showEventData();
                clear();
            }
        }catch(Exception e){

            e.printStackTrace();
        }


    }

    public void clear(){

        eventID.setText("");
        title.setText("");
        date.setText("");
        description.setText("");
        location.setText("");
        host.setText("");
        category.setText("");
        author.setText("");
        EventPic.setImage(null);
        fpath.setText("");

    }

    @FXML
    void AddPicture(ActionEvent event) {
        FileChooser open = new FileChooser();

        Stage stage = (Stage) apane.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            fpath.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            EventPic.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
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
        }
//        finally {
//            DatabaseConnection.closeConnection();
//        }

        return eventList;
    }

    public void showEventData() {
        ObservableList<Event> showEventList = eventList();

        idCol.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        pictureCol.setCellValueFactory(new PropertyValueFactory<>("Image"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        hostCol.setCellValueFactory(new PropertyValueFactory<>("host"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        eventTable.setItems(showEventList);
    }



    public void selectData() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEventData();
    }

}
