package com.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.object.info.Product;
import com.demo.object.info.User;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {


    private static final String DB_NAME = "Restaurant";
    public static final String TABLE_USER = "user";
    SQLiteDatabase db;

    public UserDatabase(@Nullable Context context) {
        super(context, TABLE_USER, null, 1);
        /*init();*/
    }

    public void addUser(User user)
    {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("fullname", user.getFullname());
        values.put("birthday", user.getBirthday());
        values.put("gender", true);
        values.put("gmail",user.getGmail());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());
        values.put("datejoin", user.getDateJoin());
        values.put("status", true);
        db.insert(TABLE_USER, null, values);

    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();
        db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", TABLE_USER);
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String fullname = cursor.getString(3);
            String birthday = cursor.getString(4);
            boolean gender = cursor.getInt(5) > 0;
            String gmail = cursor.getString(6);
            String phone = cursor.getString(7);
            String address = cursor.getString(8);
            String datejoin = cursor.getString(9);
            boolean status = cursor.getInt(10) > 0;


            users.add(new User(id,username,password,fullname,birthday,gender,gmail,phone,address,datejoin,status));
        }
        return users;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(50), password VARCHAR(50), fullname VARCHAR(50), birthday DATETIME, gender INTEGER, gmail VARCHAR(50), phone VACHAR(50), address VARCHAR(50), datejoin DATETIME, status INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
