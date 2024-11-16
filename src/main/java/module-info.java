module com.example.buecherverwaltung {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.buecherverwaltung to javafx.fxml;
    exports com.example.buecherverwaltung;
}