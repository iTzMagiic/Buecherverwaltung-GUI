package com.example.buecherverwaltung;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {

        /*
        *
    +-------------------+
    |      Stage        |  <--- Das Fenster
    |                   |
    |  +-------------+  |
    |  |    Scene    | |  <--- Die Szene
    |  |             |  |
    |  |  +-------+  |  |
    |  |  | Parent|  |  |  <--- Das Root-Layout (geladen durch FXMLLoader)
    |  |  +-------+  |  |
    |  +-------------+  |
    +-------------------+
        *
        * */


    @Override
    public void start(Stage primaryStage) {
        SceneManager.setStage(primaryStage); // Übergibt die Haupt-Stage
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Login! in iTzMagiic's Bücherverwaltung"); // Lädt die erste Szene
    }

    public static void main(String[] args) {
        launch();
    }
}