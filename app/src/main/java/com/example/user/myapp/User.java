package com.example.user.myapp;

/**
 * Created by USER on 30/04/2018.
 */

public class User {
    private String Email;
    private String UserID;

    public User(String email, String userID) {
        Email = email;
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
