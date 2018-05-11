package com.example.user.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USER on 10/05/2018.
 */

public class AppAllMessages extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="userInfo.db";
    public static final String CREATE_TABLE = "CREATE TABLE " + AllUsersMessages.UserEntry.TABLE_USERS + "("
            +AllUsersMessages.UserEntry.KEY_sender + " TEXT NOT NULL," + AllUsersMessages.UserEntry.KEY_reciever + " TEXT NOT NULL," + AllUsersMessages.UserEntry.KEY_allmessages+"TEXT NOT NULL" + ");";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS "+AllUsersMessages.UserEntry.TABLE_USERS;

    public AppAllMessages(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("DataBase Operations","database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

        Log.d("DataBase Operations","table created...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public boolean addChat(String Sender,String Reciever,String Message,SQLiteDatabase database)
    {

        ContentValues values = new ContentValues();
        String query="SELECT * FROM " +UserContract.UserEntry.TABLE_USERS;
        Cursor cursor=database.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {

                if (Sender.equals(cursor.getString(0)) && Reciever.equals(cursor.getString(1))) {
                    return false;
                }
            } while (cursor.moveToNext());
        }

        values.put(AllUsersMessages.UserEntry.KEY_sender, Sender); // Shop Name
        values.put(AllUsersMessages.UserEntry.KEY_reciever, Reciever); // Shop Phone Number
        values.put(AllUsersMessages.UserEntry.KEY_allmessages,Message);
        database.insert(UserContract.UserEntry.TABLE_USERS, null, values);
        Log.d("DataBase Operations","one raw inserted...");
        return true;




    }
}
