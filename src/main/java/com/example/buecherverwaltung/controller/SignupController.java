package com.example.buecherverwaltung.controller;

import com.example.buecherverwaltung.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SignupController{




    @FXML
    private void login(MouseEvent event) {
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml");
    }
}
