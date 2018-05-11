package com.example.user.myapp;

/**
 * Created by USER on 10/05/2018.
 */

public class AllUsersMessages {
    private AllUsersMessages(){}

    public static class UserEntry
    {
        public static final String TABLE_USERS = "users";
        public static final String KEY_sender = "sender";
        public static final String KEY_reciever = "reciever";
        public static final String KEY_allmessages="allmessages";
    }
}
