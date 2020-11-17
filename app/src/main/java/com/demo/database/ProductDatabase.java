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
            addNew(new Product(0, "Vegetable Beef Soup", 5, "Vegetable Beef Soup is the perfect cozy soup and such a good way to use up all those veggies stocked in the fridge! It’s perfectly hearty and filling thanks to the tender chunks of protein-rich beef and abundance of nutritious vegetables.",
                    4.5F, 10, true, 3));
            addNew(new Product(0, "Vegetable Soup", 4.5, "This Vegetable Soup has become one of my most popular soup recipes and for good reason! It’s healthy, it’s comforting and 1,000 times better than what you’ll get in a can! Full of flavor and so easy to make you can’t go wrong with a big warm bowl of vegetable soup.",
                    3.5F, 15, true, 3));
            addNew(new Product(0, "Green Goddess Immune Boosting Soup", 5.5, "Green Goddess Immune Boosting Soup ~ this nourishing soup is the cold weather equivalent to your power smoothie.  It’s a vibrant soup packed with everything you need to get through cold and flu season without so much as a sniffle.",
                    5.0F, 0, true, 3));
            addNew(new Product(0, "Thai Carrot Sweet Potato Soup", 5.0, "Carrots, sweet potato, and warming Thai curry blended with an unexpected ingredient—almond butter—made it a sweet and spicy, rich soup. I wish it could say it cured me for good (if only soup could), but it definitely made me feel better that evening. And the next morning, when I heated up leftovers for breakfast.",
                    4.0F, 20, true, 3));
            addNew(new Product(0, "Parsnip and Apple Soup with Black Lentils and Vegetable Crisps", 7.5, "A delicious, sweet parsnip and apple soup, loaded with lentils and roasted vegetable crisps. It’s no surprise I love me some soup. Recently I’ve made this roasted garlic, leek and tomato soup, and this 5 ingredient pea and mint soup. In these cold season, I just don’t think you can beat a nice warm, comforting soup.",
                    5.0F, 0, true, 3));
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

    public ArrayList<Product> getProductByType(int tId) {
        ArrayList<Product> products = new ArrayList<>();
        db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE typeId = %d", TABLE_PRODUCT, tId);
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

    public ArrayList<Product> getProductByName(String nameFood) {
        ArrayList<Product> products = new ArrayList<>();
        db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE name LIKE '%%%s%%'", TABLE_PRODUCT, nameFood);
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


    public ArrayList<Product> getAllProductSaleOff() {
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
            if (percentSaleOff > 0)
                products.add(new Product(id, name, price, description, rate, percentSaleOff, status, typeId));
        }
        return products;
    }

    public Product findProductById(int productId) {
        Product p = null;
        String sql = String.format("SELECT * FROM %s WHERE id = %d", TABLE_PRODUCT, productId);
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            String description = cursor.getString(3);
            float rate = cursor.getFloat(4);
            int percentSaleOff = cursor.getInt(5);
            boolean status = cursor.getInt(6) > 0;
            int typeId = cursor.getInt(7);
            p = new Product(id, name, price, description, rate, percentSaleOff, status, typeId);
        }
        return p;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), price NUMBERIC, description VARCHAR, rate NUMBERIC, percentSaleOff INTEGER, status INTEGER, typeId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
