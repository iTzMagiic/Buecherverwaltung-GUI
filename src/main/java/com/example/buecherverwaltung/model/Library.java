package com.example.buecherverwaltung.model;

import java.util.ArrayList;

public class Library {

    private final ArrayList<Book> listOfBooks;
    private final Database database;

    public Library(Database database) {
        listOfBooks = new ArrayList<>();
        this.database = database;
    }


    public ArrayList<Book> getListOfBooks() {
        return listOfBooks;
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
    }

    public boolean removeBook(String title) {
        /*
            "currentBook" ist ein temporärer Name, der jedes Objekt aus der Liste repräsentiert,
            während durch die Liste iteriert wird. Es ist ähnlich wie eine foreach-Schleife.
            Hier wird jedes "currentBook" geprüft: Wenn die Bedingung (currentBook.getTitle().equals(title))
            wahr ist, wird das Buch aus der Liste entfernt.
         */
        return listOfBooks.removeIf(currentBook -> currentBook.getTitle().toLowerCase().equals(title.toLowerCase().trim()));
    }

//    public void displayBooks(int userID) {
//        List<Book> loadedBooks = database.getAllBooks(userID);
//
//        if (loadedBooks != null) {
//            for (Book book : loadedBooks) {
//                System.out.println(book);
//            }
//        } else {
//            System.out.println("Keine Bücher in der Bibliothek.");
//        }
//        System.out.println();
//    }

}
