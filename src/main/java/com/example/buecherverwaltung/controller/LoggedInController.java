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
import javafx.scene.input.KeyEvent;
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
    private void executeAddBookMenu() {
        menu.setVisible(false);
        label_welcome.setVisible(false);

        addBookMenu.setVisible(true);
    }
    @FXML
    private void onMouseClickedAddBookMenu(MouseEvent event) {
        executeAddBookMenu();
    }
    @FXML
    private void onKeyPressedEnterAddBookMenu(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeAddBookMenu();
        }
    }

    @FXML
    private void executeAddBook() {
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
            return;
        }


        if(accountService.addBookToDatabase(title, author, yearOfPublication)) {
            session.clearBooks();
            field_title.clear();
            field_author.clear();
            field_yearOfPublication.clear();
        }
    }
    @FXML
    private void onMouseClickedAddBook(MouseEvent event) {
        executeAddBook();
    }
    @FXML
    private void onKeyPressedEnterAddBook(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeAddBook();
        }
    }

    @FXML
    private void executeExitAddBookMenu() {
        addBookMenu.setVisible(false);

        menu.setVisible(true);
        label_welcome.setVisible(true);
    }
    @FXML
    private void onMouseClickedExitAddBookMenu(MouseEvent event) {
        executeExitAddBookMenu();
    }
    @FXML
    private void onKeyPressedEnterExitAddBook(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeExitAddBookMenu();
        }
    }



    @FXML
    private void executeRemoveBookMenu() {
        label_welcome.setVisible(false);
        menu.setVisible(false);

        removeBookMenu.setVisible(true);
    }
    @FXML
    private void onMouseClickedRemoveBookMenu(MouseEvent event) {
        executeRemoveBookMenu();
    }
    @FXML
    private void onKeyPressedEnterRemoveBookMenu(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeRemoveBookMenu();
        }
    }

    @FXML
    private void executeRemoveBook() {
        AccountService accountService = new AccountService();
        String title = field_removeTitle.getText();
        UserSession session = UserSession.getInstance();

        if(accountService.removeBookFromDatabase(title)) {
            if(session.getBooks() != null) {
                session.clearBooks();
            }
            field_removeTitle.clear();
        }
    }
    @FXML
    private void onMouseClickedRemoveBook(MouseEvent event) {
        executeRemoveBook();
    }
    @FXML
    private void onKeyPressedEnterRemoveBook(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeRemoveBook();
        }
    }

    @FXML
    private void executeExitRemoveBookMenu() {
        removeBookMenu.setVisible(false);

        menu.setVisible(true);
        label_welcome.setVisible(true);
    }
    @FXML
    private void onMouseClickedExitRemoveBookMenu(MouseEvent event) {
        executeExitRemoveBookMenu();
    }
    @FXML
    private void onKeyPressedEnterExitRemoveBookMenu(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeExitRemoveBookMenu();
        }
    }



    @FXML
    private void executeShowBooksMenu() {
        menu.setVisible(false);
        label_welcome.setVisible(false);

        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        column_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        column_yearOfPublication.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));

        UserSession session = UserSession.getInstance();
        int userID = session.getUserID();


        if(session.getBooks() == null) {
            session.setBooks(database.getAllBooks(userID));
        }

        ObservableList<Book> observableBooks = FXCollections.observableArrayList(session.getBooks());
        tableView_books.setItems(observableBooks);

        showBooksMenu.setVisible(true);
    }
    @FXML
    private void onMouseClickedShowBooksMenu(MouseEvent event) {
        executeShowBooksMenu();
    }
    @FXML
    private void onKeyPressedEnterShowBooksMenu(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeShowBooksMenu();
        }
    }

    @FXML
    private void executeExitShowBooksMenu() {
        menu.setVisible(true);
        label_welcome.setVisible(true);

        showBooksMenu.setVisible(false);
    }
    @FXML
    private void onMouseClickedExitShowBooksMenu(MouseEvent event) {
        executeExitShowBooksMenu();
    }
    @FXML
    private void onKeyPressedEnterExitShowBooksMenu(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeExitShowBooksMenu();
        }
    }



    @FXML
    private void executeLogout() {
        UserSession session = UserSession.getInstance();
        session.clearSession();
        SceneManager.switchScene("/com/example/buecherverwaltung/login-view.fxml", "Login! in iTzMagiic's Bücherverwaltung");
    }
    @FXML
    private void onMouseClickedLogout(MouseEvent event) {
        executeLogout();
    }
    @FXML
    private void onKeyPressedEnterLogout(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeLogout();
        }
    }

    @FXML
    private void executeExitProgram() {
        System.exit(0);
    }
    @FXML
    private void onMouseClickedExitProgram(MouseEvent event) {
        executeExitProgram();
    }
    @FXML
    private void onKeyPressedEnterExitProgram(KeyEvent event) {
        if(event.getCode().toString().equals("ENTER")) {
            executeExitProgram();
        }
    }




}

