package com.example.buecherverwaltung.controller;


import com.example.buecherverwaltung.model.AccountService;
import com.example.buecherverwaltung.utils.SceneManager;
import com.example.buecherverwaltung.model.Database;
import com.example.buecherverwaltung.model.Rules;
import com.example.buecherverwaltung.model.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class LoginController {

    private Database database;


    @FXML
    private TextField field_username;
    @FXML
    private PasswordField field_password;




    public LoginController() {
        this.database = new Database();
    }



    @FXML
    private void login(MouseEvent event) {
        String username = field_username.getText();
        String password = field_password.getText();
        int userID = -1;

        if(!database.testConnection()) {
            Rules.showErrorAlert("Die Verbindung zur Datenbank konnte nicht hergestellt werden.");
            return;
        }
        if(username.isEmpty() || password.isEmpty()) {
            Rules.showErrorAlert("Bitte füllen Sie alle Felder aus.");
            return;
        }
        if(!database.usernameExists(username)) {
            field_username.clear();
            field_password.clear();
            Rules.showErrorAlert("Benutzername existiert nicht.");
            return;
        }

        userID = database.getUserID(username, password);

        if(userID == -1) {
            field_password.clear();
            Rules.showErrorAlert("Falsches Passwort.");
            return;
        }

        UserSession session = UserSession.getInstance();
        session.setUserID(userID);
        String name = database.getName(userID);
        session.setName(name);

        SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml", "Willkommen " + name +"");
    }


    @FXML
    private void newLogin(MouseEvent event) {
        AccountService accountService = new AccountService();

        String username = field_username.getText();
        String password = field_password.getText();

        if(accountService.loginToDatabase(username, password)) {
            SceneManager.switchScene("path", "title");
        } else {
            field_username.clear();
            field_password.clear();
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }


    @FXML
    private void signup(MouseEvent event) {
        SceneManager.switchScene("/com/example/buecherverwaltung/signup-view.fxml", "Registrier Dich jetzt!!");
    }






}
