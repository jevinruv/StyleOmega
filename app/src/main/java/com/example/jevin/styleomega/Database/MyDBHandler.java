package com.example.jevin.styleomega.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jevin on 20-Jul-17.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "styleomegaDB.db";

    //Table Names
    private static final String TABLE_USERS = "users";

    //User Table - Column Names
    private static final String KEY_NIC = "nic";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                KEY_NIC  + "TEXT PRIMARY KEY " +
                KEY_NAME + "TEXT"+
                KEY_PASSWORD + "TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){


    }
}
