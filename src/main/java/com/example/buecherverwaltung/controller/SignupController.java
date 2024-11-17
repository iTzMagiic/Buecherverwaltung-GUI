package com.example.buecherverwaltung.controller;

import com.example.buecherverwaltung.SceneManager;
import com.example.buecherverwaltung.utils.Database;
import com.example.buecherverwaltung.utils.Rules;
import com.example.buecherverwaltung.utils.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController{

    Database database = new Database();

    @FXML
    private TextField field_username;
    @FXML
    private TextField field_name;
    @FXML
    private PasswordField field_password;




    @FXML
    private void login(MouseEvent event) {
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Login! in iTzMagiic Bücherverwaltung !!");
    }

    @FXML
    private void createAccount(MouseEvent event) {
        String username = field_username.getText();
        String password = field_password.getText();
        String name = field_name.getText();
        int userID = -1;

        if(!database.testConnection()) {
            Rules.showErrorAlert("Die Verbindung zur Datenbank konnte nicht hergestellt werden.");
            return;
        }
        if(username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            Rules.showErrorAlert("Bitte füllen Sie alle Felder aus.");
            return;
        }
        if(!Rules.isUsernameValid(username)) {
            field_username.clear();
            Rules.showErrorAlert("Der Benutzername darf nur Buchstaben, Zahlen enthalten und mindestens 4 Zeichen lang sein.");
            return;
        }
        if(database.usernameExists(username)) {
            field_username.clear();
            Rules.showErrorAlert("Benutzername ist schon vergeben.");
            return;
        }
        if(!Rules.isPasswordValid(password)) {
            field_password.clear();
            Rules.showErrorAlert("Das Passwort muss mindestens 8 Zeichen lang sein, einen Großbuchstaben, eine Zahl und ein Sonderzeichen enthalten.");
            return;
        }
        if(!Rules.isNameValid(name)) {
            field_name.clear();
            Rules.showErrorAlert("Der Name darf nur Buchstaben enthalten und nicht Leer sein.");
            return;
        }

        if(database.createUser(name, username, password)) {
            UserSession session = UserSession.getInstance();
            session.setName(name);
            SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Logg dich ein " + name + "!!!");
        }
    }






}
