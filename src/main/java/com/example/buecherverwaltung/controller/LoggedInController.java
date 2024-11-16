package com.example.buecherverwaltung.controller;


import com.example.buecherverwaltung.SceneManager;
import com.example.buecherverwaltung.utils.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;




public class LoggedInController {


    @FXML
    private Label label_welcome;


    public void initialize() {
        UserSession session = UserSession.getInstance();
        String name = session.getName();
        label_welcome.setText("Willkommen " + name);
    }

    @FXML
    private void logout(MouseEvent event) {
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Login! in iTzMagiic's Bücherverwaltung");
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }



}
