package com.example.buecherverwaltung;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private String user;
    private String pass;

    @FXML
    void submitLogin(MouseEvent event) {
        user = username.getText();
        pass = password.getText();

        username.setVisible(false);
        password.setVisible(false);


        System.out.println("Ihr Benutzername: " + user + "\nIhr Passwort: " + pass);
    }

}
