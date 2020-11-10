package com.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.object.info.Type;

import java.util.ArrayList;

public class TypeDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Restaurant";
    public static final String TABLE_TYPE = "Type";
    SQLiteDatabase db;

    public TypeDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        init();
    }


    public void addNew(String name) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("status", true);
        db.insert(TABLE_TYPE, null, values);
    }

    private void init() {
        if (getAllType().size() == 0) {
            addNew("Hotpot");
            addNew("Stir fried");
            addNew("Soup");
            addNew("Dessert");
            addNew("Drink");
        }
    }

    public ArrayList<Type> getAllType() {
        ArrayList<Type> types = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "SELECT * FROM Type";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            boolean status = cursor.getInt(2) > 0;
            types.add(new Type(id, name, status));
        }
        return types;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TYPE + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), status BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
