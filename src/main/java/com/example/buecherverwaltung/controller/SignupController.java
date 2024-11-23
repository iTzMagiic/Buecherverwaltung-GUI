package com.example.buecherverwaltung.controller;

import com.example.buecherverwaltung.model.AccountService;
import com.example.buecherverwaltung.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class SignupController{

    @FXML
    private TextField field_username;
    @FXML
    private TextField field_name;
    @FXML
    private PasswordField field_password;




    @FXML
    private void executeLoginMenu() {
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Login! in iTzMagiic Bücherverwaltung !!");
    }
    @FXML
    private void onMouseClickedLogin(MouseEvent event) {
        executeLoginMenu();
    }
    @FXML
    private void onKeyPressedEnterLogin(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeLoginMenu();
        }
    }



    @FXML
    private void executeCreateAccount() {
        AccountService accountService = new AccountService();

        String username = field_username.getText();
        String password = field_password.getText();
        String name = field_name.getText();

        if(accountService.signUpToDatabase(username, password, name)) {
            SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Logg dich ein " + name + "!!!");
        } else {
            field_username.clear();
            field_password.clear();
            field_name.clear();
        }
    }
    @FXML
    private void onMouseClickedCreateAccount(MouseEvent event) {
        executeCreateAccount();
    }
    @FXML
    private void onKeyPressedEnterCreateAccount(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeCreateAccount();
        }
    }






}
