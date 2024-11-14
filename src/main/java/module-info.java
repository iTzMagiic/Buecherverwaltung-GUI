module com.example.buecherverwaltung {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.buecherverwaltung to javafx.fxml;
    exports com.example.buecherverwaltung;
}