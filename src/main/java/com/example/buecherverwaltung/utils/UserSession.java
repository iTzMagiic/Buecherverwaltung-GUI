package com.example.buecherverwaltung.utils;

public class UserSession {

    private static UserSession instance;

    private int userID;
    private String name;


    public UserSession() {

    }

    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void clearSession() {
        userID = 0;
        name = null;
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


}
