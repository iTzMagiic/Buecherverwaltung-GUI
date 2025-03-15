package com.example.buecherverwaltung.controller;

import com.example.buecherverwaltung.model.AccountService;
import com.example.buecherverwaltung.utils.SceneManager;
import com.example.buecherverwaltung.model.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class LoginController {

    @FXML
    private TextField field_username;
    @FXML
    private PasswordField field_password;


    @FXML
    private void executeLogin() {
        AccountService accountService = new AccountService();

        String username = field_username.getText();
        String password = field_password.getText();

        if (accountService.loginToDatabase(username, password)) {
            UserSession session = UserSession.getInstance();

            SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml", "Willkommen " + session.getName());
        } else {
            field_username.clear();
            field_password.clear();
        }
    }

    @FXML
    private void onMouseClickedLogin(MouseEvent event) {
        executeLogin();
    }

    @FXML
    private void onKeyPressedEnterLogin(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            executeLogin();
        }
    }


    @FXML
    private void executeExit() {
        System.exit(0);
    }

    @FXML
    private void onMouseClickedExit(MouseEvent event) {
        executeExit();
    }

    @FXML
    private void onKeyPressedExit(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            executeExit();
        }
    }


    @FXML
    private void executeSignup() {
        SceneManager.switchScene("/com/example/buecherverwaltung/signup-view.fxml", "Registrier Dich jetzt!!");
    }

    @FXML
    private void onMouseClickedSignup(MouseEvent event) {
        executeSignup();
    }

    @FXML
    private void onKeyPressedEnterSignup(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            executeSignup();
        }
    }


}
