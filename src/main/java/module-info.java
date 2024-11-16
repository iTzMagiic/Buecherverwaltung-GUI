module com.example.buecherverwaltung {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.buecherverwaltung to javafx.fxml;
    exports com.example.buecherverwaltung;
    exports com.example.buecherverwaltung.controller;
    opens com.example.buecherverwaltung.controller to javafx.fxml;
    exports com.example.buecherverwaltung.utils;
    opens com.example.buecherverwaltung.utils to javafx.fxml;
}