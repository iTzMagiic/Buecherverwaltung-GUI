package com.example.buecherverwaltung.model;

import java.util.List;

public class UserSession {

    /*
     * Diese Klasse implementiert das "Singleton-Pattern".
     * Ein Objekt der Klasse kann nur über die Methode getInstance() erstellt werden.
     * Dadurch wird sichergestellt, dass es in der gesamten Anwendung
     * nur eine einzige Instanz der Klasse gibt.
     */


    private static UserSession instance;
    private int userID;
    private String name;
    private List<Book> books;


    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void clearSession() {
        userID = 0;
        name = null;
        clearBooks();
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void clearBooks() {
        books = null;
    }

}
