package com.example.jevin.styleomega.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jevin.styleomega.Model.Item;
import com.example.jevin.styleomega.Model.User;

/**
 * Created by Jevin on 8/12/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "styleomegaDB.db";

    //Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ITEMS = "items";

    //User Table - Column Names
    private static final String USER_COLUMN_NIC = "nic";
    private static final String USER_COLUMN_EMAIL = "email";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_PASSWORD = "password";

    //Item Table - Column Names
    private static final String ITEM_COLUMN_ID = "id";
    private static final String ITEM_COLUMN_NAME = "name";
    private static final String ITEM_COLUMN_PRICE = "price";
    private static final String ITEM_COLUMN_IMAGE = "image";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                USER_COLUMN_NIC + " TEXT PRIMARY KEY," +
                USER_COLUMN_NAME + " TEXT," +
                USER_COLUMN_EMAIL + " TEXT," +
                USER_COLUMN_PASSWORD + " TEXT" + ")";

        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
                ITEM_COLUMN_ID + " TEXT PRIMARY KEY," +
                ITEM_COLUMN_NAME + " TEXT," +
                ITEM_COLUMN_IMAGE + " TEXT," +
                ITEM_COLUMN_PRICE + " TEXT" + ")";


        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }


    /******************** Users Table *******************************************/

    public void addUser(User user) {

        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_NIC, user.getNic());
        values.put(USER_COLUMN_NAME, user.getName());
        values.put(USER_COLUMN_EMAIL, user.getEmail());
        values.put(USER_COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase db = getWritableDatabase();
        try {
            db.insert(TABLE_USERS, null, values);
        } catch (Exception er) {

        }
        db.close();
    }

    public User viewUser(String nic) {

        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        //Cursor cursor=db.rawQuery("SELECT * FROM users WHERE email='"+email+"'", null);

        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + USER_COLUMN_NIC + "='" + nic + "' ";

        try {
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                user = new User();
                user.setNic(c.getString(c.getColumnIndex(USER_COLUMN_NIC)));
                user.setName(c.getString(c.getColumnIndex(USER_COLUMN_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(USER_COLUMN_EMAIL)));
                user.setPassword(c.getString(c.getColumnIndex(USER_COLUMN_PASSWORD)));
            }
        } catch (SQLException er) {

        }

        return user;
    }

    public User viewUserAny(String column, String value) {
        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + column + "='" + value + "' ";

        try {
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                user = new User();
                user.setNic(c.getString(c.getColumnIndex(USER_COLUMN_NIC)));
                user.setName(c.getString(c.getColumnIndex(USER_COLUMN_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(USER_COLUMN_EMAIL)));
                user.setPassword(c.getString(c.getColumnIndex(USER_COLUMN_PASSWORD)));
            }
        } catch (SQLException er) {

        }

        return user;
    }

    public String isUserExist(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                USER_COLUMN_NIC
        };
        String selection = USER_COLUMN_EMAIL + "= ?" + " AND " + USER_COLUMN_PASSWORD + "= ?";

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


        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(USER_COLUMN_NIC));
        }
        return null;
    }

    public void updateUser(String nic, String field, String value) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(field, value);
        db.update(TABLE_USERS, cv, USER_COLUMN_NIC + " =" + nic, null);

    }


    //********************* Items Table******************************************/

    public Item viewItem(String id) {

        SQLiteDatabase db = getReadableDatabase();
        Item item = null;

        String selectItemQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + ITEM_COLUMN_ID + "='" + id + " ";

        try {
            Cursor c = db.rawQuery(selectItemQuery, null);
            if (c.moveToFirst()) {
                item = new Item();
                item.setId(c.getString(c.getColumnIndex(ITEM_COLUMN_ID)));
                item.setName(c.getString(c.getColumnIndex(ITEM_COLUMN_NAME)));
                item.setPrice(c.getDouble(c.getColumnIndex(ITEM_COLUMN_PRICE)));
                item.setImage(c.getString(c.getColumnIndex(ITEM_COLUMN_IMAGE)));
            }
        } catch (SQLException er) {

        }

        return item;
    }
}


