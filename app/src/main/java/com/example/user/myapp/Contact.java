package com.example.user.myapp;

/**
 * Created by USER on 02/05/2018.
 */

public class Contact {
    private String Email;
    private String UserID;

    public Contact(String email, String userID) {
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
