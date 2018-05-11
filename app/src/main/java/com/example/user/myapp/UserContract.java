package com.example.user.myapp;

/**
 * Created by USER on 30/04/2018.
 */

public class UserContract {
    private UserContract(){}

    public static class UserEntry
    {
        public static final String TABLE_USERS = "users";
        public static final String KEY_email = "email";
        public static final String KEY_userId = "userid";
        public static final String KEY_contacter="contacter";
    }
}
