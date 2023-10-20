module com.example.splogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.splogin to javafx.fxml;
    exports com.example.splogin;
    exports com.example.splogin.controllers;
    opens com.example.splogin.controllers to javafx.fxml;
    exports com.example.splogin.utils;
    opens com.example.splogin.utils to javafx.fxml;
    exports com.example.splogin.models;
    opens com.example.splogin.models to javafx.fxml;
}