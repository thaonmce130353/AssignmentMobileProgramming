package com.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.object.info.Order;
import com.demo.object.info.OrderDetail;

import java.util.ArrayList;

public class OrderDetailDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Restaurant";
    public static final String TABLE_TYPE = "OrderDetailFood";

    SQLiteDatabase db;

    public OrderDetailDatabase(@Nullable Context context) {
        super(context, TABLE_TYPE, null, 1);
        init();
    }

    private void init() {
        if (getAllOrderDetail().size() == 0) {
//            addNewOrderDetail(new OrderDetail(5,5,0,4,20,1,1));
//            addNewOrderDetail(new OrderDetail(10,10,0,3,30,1,2));
//            addNewOrderDetail(new OrderDetail(8,8,0,5,40,2,3));
//            addNewOrderDetail(new OrderDetail(10,10,0,3,30,3,2));
//            addNewOrderDetail(new OrderDetail(5,5,0,4,20,4,1));
//            addNewOrderDetail(new OrderDetail(10,10,0,1,10,5,2));
        }
    }

    public void addNewOrderDetail(OrderDetail orderDetail) {

        db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("price", orderDetail.getPrice());
        values.put("priceAfterSaleOff", orderDetail.getPriceAfterSaleOff());
        values.put("percentSaleOff", orderDetail.getPercentSaleOff());
        values.put("quantity", orderDetail.getQuantity());
        values.put("total", orderDetail.getTotal());
        values.put("orderId", orderDetail.getOrderId());
        values.put("productId", orderDetail.getProductId());

        db.insert(TABLE_TYPE, null, values);
    }

    public ArrayList<OrderDetail> getAllOrderDetail() {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "SELECT * FROM OrderDetailFood";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int orderId = cursor.getInt(6);
            int oderDetailId = cursor.getInt(0);
            int productId = cursor.getInt(7);
            int percentSaleOff = cursor.getInt(3);
            int quantity = cursor.getInt(4);
            float price = cursor.getFloat(1);
            float total = cursor.getFloat(5);
            float priceAfterSaleOff = cursor.getFloat(2);
            orderDetails.add(new OrderDetail(oderDetailId,price, priceAfterSaleOff, percentSaleOff, quantity, total, orderId, productId));
        }
        return orderDetails;
    }

    public ArrayList<OrderDetail> getAllOrderDetailByOrderId(int ordId) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "SELECT * FROM OrderDetailFood WHERE orderId = " + ordId + " ";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int orderId = cursor.getInt(6);
            int oderDetailId = cursor.getInt(0);
            int productId = cursor.getInt(7);
            int percentSaleOff = cursor.getInt(3);
            int quantity = cursor.getInt(4);
            float price = cursor.getFloat(1);
            float total = cursor.getFloat(5);
            float priceAfterSaleOff = cursor.getFloat(2);
            orderDetails.add(new OrderDetail(oderDetailId, price, priceAfterSaleOff, percentSaleOff, quantity, total, orderId, productId));
        }
        return orderDetails;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TYPE +
                "(oderDetailId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "price FLOAT, priceAfterSaleOff FLOAT, percentSaleOff INTEGER, " +
                "quantity INTEGER, total FLOAT, orderId INTEGER, productId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
