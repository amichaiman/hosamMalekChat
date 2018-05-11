package com.example.user.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by USER on 30/04/2018.
 */

public class AppDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="userschat.db";
    public static final String CREATE_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_USERS + "("
            +UserContract.UserEntry.KEY_email + " TEXT NOT NULL," + UserContract.UserEntry.KEY_userId + " TEXT NOT NULL" + ");";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS "+ UserContract.UserEntry.TABLE_USERS;

    public AppDBHelper(Context context) {
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
    public boolean addUser(User user,SQLiteDatabase database)
    {

        ContentValues values = new ContentValues();
        String query="SELECT * FROM " +UserContract.UserEntry.TABLE_USERS;
        Cursor cursor=database.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {

                if (user.getUserID().equals(cursor.getString(0))) {
                    return false;
                }
            } while (cursor.moveToNext());
        }

        values.put(UserContract.UserEntry.KEY_email, user.getEmail()); // Shop Name
        values.put(UserContract.UserEntry.KEY_userId, user.getUserID()); // Shop Phone Number
        database.insert(UserContract.UserEntry.TABLE_USERS, null, values);
        Log.d("DataBase Operations","one raw inserted...");
        return true;




    }
    public Cursor getContactName(SQLiteDatabase database)
    {
        String[] Columns={UserContract.UserEntry.KEY_email,UserContract.UserEntry.KEY_userId};
        Cursor cursor=database.query(UserContract.UserEntry.TABLE_USERS,Columns,null,null,null,null,null);
        return  cursor;
    }
}
