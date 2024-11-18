package com.example.buecherverwaltung.controller;


import com.example.buecherverwaltung.model.*;
import com.example.buecherverwaltung.utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class LoggedInController {


    Database database;

    @FXML
    private Label label_welcome;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane addBookMenu;
    @FXML
    private AnchorPane removeBookMenu;
    @FXML
    private AnchorPane showBooksMenu;
    @FXML
    private TextField field_removeTitle;
    @FXML
    private TextField field_title;
    @FXML
    private TextField field_author;
    @FXML
    private TextField field_yearOfPublication;
    @FXML
    private TableView<Book> tableView_books;
    @FXML
    private TableColumn<Book, String> column_title;
    @FXML
    private TableColumn<Book, String> column_author;
    @FXML
    private TableColumn<Book, Integer> column_yearOfPublication;



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
        int yearOfPublication = -1;
        UserSession session = UserSession.getInstance();

        if(!Rules.isValidTitle(title)) {
            Rules.showErrorAlert("Titel darf nicht leer sein und darf nur Buchstaben, Ziffern und Leerzeichen enthalten.");
            return;
        }
        if(!Rules.isValidAuthor(author)) {
            Rules.showErrorAlert("Autor darf nicht leer sein und darf nur Buchstaben und Leerzeichen enthalten.");
            return;
        }
        if(!Rules.isValidYearText(yearOfPublicationText)) {
            Rules.showErrorAlert("Das Veröffentlichungsjahr ist falsch. Bitte geben Sie ein gültiges Jahr ein.");
            return;
        }

        try {
            yearOfPublication = Integer.parseInt(yearOfPublicationText);
        } catch (NumberFormatException e) {
            System.err.println("Fehler beim Formatieren von String zu Integer. " + e.getMessage());
        }

        if(yearOfPublication != -1) {
            database.addBook(title, author, yearOfPublication, session.getUserID());
            Rules.showConfirmAlert("Das Buch wurde erfolgreich hinzugefügt.");
            SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml", "Willkommen " + session.getName());
        }

    }

    @FXML
    private void exitAddBookMenu(MouseEvent event) {
        addBookMenu.setVisible(false);

        menu.setVisible(true);
        label_welcome.setVisible(true);
    }

    @FXML
    private void addBook(MouseEvent event) {
        AccountService accountService = new AccountService();

        UserSession session = UserSession.getInstance();
        String name = session.getName();

        String title = field_title.getText();
        String author = field_author.getText();
        String yearOfPublicationText = field_yearOfPublication.getText();
        int yearOfPublication = -1;

        try {
            yearOfPublication = Integer.parseInt(yearOfPublicationText);
        } catch (NumberFormatException e) {
            Rules.showErrorAlert("Das Veröffentlichungsjahr muss eine gültige Zahl sein.");
            System.err.println("Fehler beim Formatieren von String zu Integer. " + e.getMessage());
        }

//        if(accountService.addBookToDatabase(title, author, yearOfPublication)) {
//            SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml", "Willkommen " + session.getName());
//        }
        if(accountService.addBookToDatabase(title, author, yearOfPublication)) {
            session.getBooks().clear(); // Prüfen wenn man eine Leer liste, leer was passiert?
            SceneManager.switchScene("/com/example/buecherverwaltung/loggedin-view.fxml", "Willkommen " + session.getName());
        }
    }

    @FXML
    private void removeBookMenu(MouseEvent event) {
        label_welcome.setVisible(false);
        menu.setVisible(false);

        removeBookMenu.setVisible(true);
    }

    @FXML
    private void removeBook(MouseEvent event) {
        AccountService accountService = new AccountService();
        String title = field_removeTitle.getText();
        UserSession session = UserSession.getInstance();

        if(accountService.removeBookFromDatabase(title)) {
            session.getBooks().clear();
//            removeBookMenu.setVisible(false);
//            menu.setVisible(true);
//            label_welcome.setVisible(true);
        }
    }

    @FXML
    private void exitRemoveBookMenu(MouseEvent event) {
        removeBookMenu.setVisible(false);

        menu.setVisible(true);
        label_welcome.setVisible(true);
    }

    @FXML
    private void showBooksMenu(MouseEvent event) {
        menu.setVisible(false);
        label_welcome.setVisible(false);

        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        column_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        column_yearOfPublication.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));

        UserSession session = UserSession.getInstance();
        int userID = session.getUserID();

        if(session.getBooks().isEmpty()) {
            session.setBooks(database.getAllBooks(userID));
        }

        ObservableList<Book> observableBooks = FXCollections.observableArrayList(session.getBooks());
        tableView_books.setItems(observableBooks);

        showBooksMenu.setVisible(true);
    }

    @FXML
    private void exitShowBooksMenu(MouseEvent event) {
        menu.setVisible(true);
        label_welcome.setVisible(true);

        showBooksMenu.setVisible(false);
    }




}

