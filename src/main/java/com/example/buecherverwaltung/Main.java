package com.example.buecherverwaltung;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Log in!");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager.setStage(primaryStage);
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml");
        primaryStage.setTitle("Login!");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}