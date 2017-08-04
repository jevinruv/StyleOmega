package com.example.jevin.styleomega.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jevin.styleomega.Model.User;

/**
 * Created by Jevin on 20-Jul-17.
 */

public class UserDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "styleomegaDB.db";

    //Table Names
    private static final String TABLE_USERS = "users";

    //User Table - Column Names
    private static final String COLUMN_NIC = "nic";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_NIC  + " TEXT PRIMARY KEY," +
                COLUMN_NAME + " TEXT,"+
                COLUMN_EMAIL + " TEXT,"+
                COLUMN_PASSWORD + " TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){

        ContentValues values = new ContentValues();

        values.put(COLUMN_NIC,user.getNic());
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.insert(TABLE_USERS,null,values);
        }
        catch (Exception er){

        }
        db.close();
    }

    public User viewUser(String nic){

        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        //Cursor cursor=db.rawQuery("SELECT * FROM users WHERE email='"+email+"'", null);

        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_NIC + "='"+nic+"' "  ;

        try {
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                user = new User();
                user.setNic(c.getString(c.getColumnIndex(COLUMN_NIC)));
                user.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
                user.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
            }
        }catch (SQLException er){

        }

        return user;
    }


    public boolean isUserExist(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                COLUMN_NIC
        };
        String selection = COLUMN_EMAIL + "= ?" + " AND " + COLUMN_PASSWORD + "= ?";

        String[] selectionArgs = {email, password};

        //Cursor cursor=db.rawQuery("SELECT * FROM users WHERE email='"+email+"'", null);

        Cursor cursor = db.query(
                TABLE_USERS,                              // The table to query
                columns,                                  // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                     // The sort order
        );


        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
