package com.example.splogin.utils;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PageUtils {
    public void loadPage(String FXMLPath) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }

    public static void closePage(Node source){
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void showAlertError(String header, String context){
        Alert forgot = new Alert(Alert.AlertType.ERROR);
        forgot.setHeaderText(header);
        forgot.setContentText(context);
        forgot.showAndWait();
    }

    public static void showAlertInformation(String header, String context){
        Alert forgot = new Alert(Alert.AlertType.INFORMATION);
        forgot.setHeaderText(header);
        forgot.setContentText(context);
        forgot.showAndWait();
    }

    public static boolean showAlertConfirmation(String header, String context){
        Alert forgot = new Alert(Alert.AlertType.CONFIRMATION);
        forgot.setHeaderText(header);
        forgot.setContentText(context);
        forgot.showAndWait();

        return forgot.getResult() != ButtonType.CANCEL;
    }
}
