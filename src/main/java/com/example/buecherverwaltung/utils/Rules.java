package com.example.buecherverwaltung.utils;

import javafx.scene.control.Alert;

public class Rules {

    public static boolean isUsernameValid(String username) {
        return username != null && username.matches("(?=(.*[a-zA-ZäöüÄÖÜß]){2,})[a-zA-ZäöüÄÖÜß0-9]{4,}");

    }

    public static boolean isNameValid(String name) {
        return name != null && name.matches("[a-zA-ZäöüÄÖÜß]+");
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$");
    }

    public static void showAlert(String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Standardmäßig ein Info-Dialog
        alert.setTitle("Fehler!");
        alert.setHeaderText(header);
        alert.showAndWait(); // Zeigt den Dialog an und wartet, bis der Benutzer ihn schließt
    }
}
