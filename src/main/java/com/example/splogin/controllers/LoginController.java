package com.example.splogin.controllers;

import com.example.splogin.utils.FileUtils;
import com.example.splogin.utils.PageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.HashMap;

public class LoginController {

    @FXML
    private ImageView myView;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button SignInButton;

    @FXML
    void forgotPassword(ActionEvent event) {
        PageUtils.showAlertInformation("CREDENTIALS FORGOTTEN", "MESSAGE AN ADMIN TO RECOVER YOUR CREDENTIALS");
    }

    @FXML
    void rememberMe(ActionEvent event) {

    }

    @FXML
    void signin(ActionEvent event) throws IOException {

        String email = emailField.getText();
        String password = passwordField.getText();
        PageUtils pageUtils = new PageUtils();

        if (email.equals("a") && password.equals("a")){
            pageUtils.loadPage("/com/example/splogin/views/EventScreen.fxml");

        }

    }

}
