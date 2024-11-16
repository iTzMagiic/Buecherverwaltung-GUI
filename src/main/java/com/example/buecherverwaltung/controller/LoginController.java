package com.example.buecherverwaltung.controller;


import com.example.buecherverwaltung.SceneManager;
import com.example.buecherverwaltung.utils.Database;
import com.example.buecherverwaltung.utils.UserSession;
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

        if(database.validUsername(username)) {
            userID = database.getUserID(username, password);
            if(userID != -1) {
                UserSession session = UserSession.getInstance();
                session.setUserID(userID);
                session.setName(database.getName(userID));
                SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml");
            } else {
                System.out.println("Falsches Passwort!");
            }
        } else {
            System.out.println("Benutzername existiert nicht!");
        }
    }

    @FXML
    private void signup(MouseEvent event) {
        SceneManager.switchScene("/com/example/buecherverwaltung/signup-view.fxml");
    }



}
