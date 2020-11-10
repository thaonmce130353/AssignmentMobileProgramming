package com.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.object.info.Product;

import java.util.ArrayList;

public class ProductDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Restaurant";
    public static final String TABLE_PRODUCT = "Product";
    SQLiteDatabase db;

    public ProductDatabase(@Nullable Context context) {
        super(context, TABLE_PRODUCT, null, 1);
        init();
    }

    public void init() {
        if (getAllProduct().size() == 0) {
            addNew(new Product(0, "Tiger", 0.9, "Tiger is a light beer", 4.5F, 0, true, 5));
        }
    }

    public void addNew(Product product) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("description", product.getDescription());
        values.put("rate", product.getRate());
        values.put("percentSaleOff", product.getPercentSaleOff());
        values.put("status", true);
        values.put("typeId", product.getTypeId());
        db.insert(TABLE_PRODUCT, null, values);
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", TABLE_PRODUCT);
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            String description = cursor.getString(3);
            float rate = cursor.getFloat(4);
            int percentSaleOff = cursor.getInt(5);
            boolean status = cursor.getInt(6) > 0;
            int typeId = cursor.getInt(7);

            products.add(new Product(id, name, price, description, rate, percentSaleOff, status, typeId));
        }
        return products;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), price NUMBERIC, description VARCHAR, rate NUMBERIC, percentSaleOff INTEGER, status INTEGER, typeId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
