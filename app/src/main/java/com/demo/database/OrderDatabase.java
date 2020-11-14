package com.demo.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.object.info.Order;
import com.demo.object.info.Type;

import java.util.ArrayList;
import java.util.Date;

public class OrderDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Restaurant";
    public static final String TABLE_TYPE = "OrderFood";
    SQLiteDatabase db;

    public OrderDatabase(@Nullable Context context) {
        super(context, TABLE_TYPE, null, 1);
        init();
    }

    public void addNewOrder(Order order) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("totalMoney", order.getTotalMoney());
        values.put("tableId", order.getTableId());
        values.put("userId", order.getUserId());
        values.put("orderDay", String.valueOf(order.getOrderDay()));
        db.insert(TABLE_TYPE, null, values);
    }

    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> orders = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "SELECT * FROM OrderFood";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int tableId = cursor.getInt(2);
            int userId = cursor.getInt(3);
            float totalMoney = cursor.getFloat(1);
            String orderDay = cursor.getString(4);
            orders.add(new Order(id, totalMoney, orderDay, tableId, userId));
        }
        return orders;
    }

    public ArrayList<Order> getAllOrderByUserId(int uId) {
        ArrayList<Order> orders = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "SELECT * FROM OrderFood WHERE userId = " + uId + " ";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int tableId = cursor.getInt(4);
            int userId = cursor.getInt(3);
            float totalMoney = cursor.getFloat(1);
            String orderDay = cursor.getString(2);
            orders.add(new Order(id, totalMoney, orderDay, tableId, userId));
        }
        return orders;
    }

    private void init() {
        if (getAllOrder().size() == 0) {
            addNewOrder(new Order(1, 50, "10/2/2020", 1, 1));
            addNewOrder(new Order(2, 40, "10/2/2020", 2, 2));
            addNewOrder(new Order(3, 30, "09/2/2020", 3, 2));
            addNewOrder(new Order(4, 20, "09/2/2020", 4, 3));
            addNewOrder(new Order(5, 10, "08/2/2020", 5, 3));
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TYPE +
                "(orderId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "totalMoney FLOAT, orderDay DATE, tableId INTEGER, userId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
