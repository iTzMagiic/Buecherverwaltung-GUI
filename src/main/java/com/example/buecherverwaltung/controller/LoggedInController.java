package com.example.buecherverwaltung.controller;


import com.example.buecherverwaltung.SceneManager;
import com.example.buecherverwaltung.utils.Database;
import com.example.buecherverwaltung.utils.Rules;
import com.example.buecherverwaltung.utils.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class LoggedInController {


    Database database;

    @FXML
    private Label label_welcome;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane addBookMenu;
    @FXML
    private TextField field_title;
    @FXML
    private TextField field_author;
    @FXML
    private TextField field_yearOfPublication;



    public void initialize() {
        UserSession session = UserSession.getInstance();
        String name = session.getName();
        label_welcome.setText("Willkommen " + name);

        database = new Database();
    }

    @FXML
    private void addBookMenu(MouseEvent event) {
        menu.setVisible(false);
        label_welcome.setVisible(false);

        addBookMenu.setVisible(true);
    }

    @FXML
    private void logout(MouseEvent event) {
        UserSession session = UserSession.getInstance();
        session.clearSession();
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Login! in iTzMagiic's Bücherverwaltung");
    }

    @FXML
    private void exitProgram(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void addBookToDatabase(MouseEvent event) {
        String title = field_title.getText();
        String author = field_author.getText();
        String yearOfPublicationText = field_yearOfPublication.getText();
        int yearOfPublication;
        UserSession session = UserSession.getInstance();

        if(!Rules.isValidTitle(title)) {
            Rules.showErrorAlert("Titel darf nicht leer sein & der Titel darf nur Buchstaben, Ziffern und Leerzeichen enthalten.");
            return;
        }
        if(!Rules.isValidAuthor(author)) {
            Rules.showErrorAlert("Autor darf nicht leer sein & Autor darf nur Buchstaben und Leerzeichen enthalten.");
            return;
        }
        if(!Rules.isValidYear(yearOfPublicationText)) {
            Rules.showErrorAlert("Das Veröffentlichungsjahr ist falsch. Bitte geben Sie ein gültiges Jahr ein.");
            return;
        }

//        if (title == null || title.trim().isEmpty()) {
//            Rules.showErrorAlert("Titel darf nicht leer sein.");
//            return;
//        }
//        if (!title.matches("[a-zA-Z0-9 ]+")) {
//            /*
//                Der reguläre Ausdruck "[a-zA-Z0-9 ]+" erlaubt:
//                - Buchstaben von a bis z und A-Z
//                - Ziffern von 0 bis 9
//                - Leerzeichen (nach der '9' steht ein Leerzeichen im Ausdruck)
//                das "+" bedeutet, dass mindestens ein Zeichen vorhanden sein muss.
//             */
//            Rules.showErrorAlert("Titel darf nur Buchstaben, Ziffern und Leerzeichen enthalten.");
//            return;
//        }
//        if (author == null || author.trim().isEmpty()) {
//            Rules.showErrorAlert("Autor darf nicht leer sein.");
//            return;
//        }
//        if (!author.matches("[a-zA-Z ]+")) {
//            Rules.showErrorAlert("Autor darf nur Buchstaben und Leerzeichen enthalten.");
//            return;
//        }
//        try {
//            yearOfPublication = Integer.parseInt(yearOfPublicationText);
//            if (yearOfPublication <= 1900 || yearOfPublication >= 2025) {
//                Rules.showErrorAlert("Das Veröffentlichungsjahr ist falsch.");
//                return;
//            }
//        } catch (NumberFormatException e) {
//            Rules.showErrorAlert("Bitte geben Sie ein gültiges Jahr ein.");
//            System.err.println("Fehler beim Konventieren von String zu Integer. " + e.getMessage());
//            return;
//        }

        database.addBook(title, author, yearOfPublication, session.getUserID());
        Rules.showConfirmAlert("Das Buch wurde erfolgreich hinzugefügt.");
        SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml", "Willkommen " + session.getName());
    }

    @FXML
    private void exitAddBookMenu(MouseEvent event) {
        addBookMenu.setVisible(false);

        menu.setVisible(true);
        label_welcome.setVisible(true);
    }



}
