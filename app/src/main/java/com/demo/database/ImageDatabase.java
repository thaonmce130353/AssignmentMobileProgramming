package com.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.Nullable;

import com.demo.assignmentmobileprogramming.R;
import com.demo.object.info.Image;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Restaurant";
    public static final String TABLE_IMAGE = "Image";
    SQLiteDatabase db;
    Context context;

    public ImageDatabase(@Nullable Context context) {
        super(context, TABLE_IMAGE, null, 1);
        this.context = context;
        init();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_IMAGE + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, url BLOB, status BOOLEAN, productId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNew(Image image) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("url", image.getUrl());
        values.put("status", true);
        values.put("productId", image.getProductId());
        db.insert(TABLE_IMAGE, null, values);
    }

    public ArrayList<Image> getAllImage() {
        ArrayList<Image> images = new ArrayList<>();
        db = getWritableDatabase();
        String sql = String.format("SELECT * FROM %s", TABLE_IMAGE);
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            byte[] url = cursor.getBlob(1);
            boolean status = cursor.getInt(2) > 0;
            int productId = cursor.getInt(3);
            images.add(new Image(id, url, status, productId));
        }
        return images;
    }

    public ArrayList<Image> getImageByProductId(int pId) {
        ArrayList<Image> images = new ArrayList<>();
        db = getWritableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE productId = %d", TABLE_IMAGE, pId);
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            byte[] url = cursor.getBlob(1);
            boolean status = cursor.getInt(2) > 0;
            int productId = cursor.getInt(3);
            images.add(new Image(id, url, status, productId));
        }
        return images;
    }

    private void init() {
        if (getAllImage().size() == 0) {
            byte[] url = null;
            Bitmap bitmap = null;
            BitmapDrawable bitmapDrawable = null;
            ByteArrayOutputStream byteArrayOutputStream = null;

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.vegetable_beef_soup);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 1));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.vegetable_soup_recipe);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 2));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.green_goddess_immune_boosting_soup);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 3));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.thai_carrot_sweet_potato_soup);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 4));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.parsnipandapple_soup_with_blacklentilsandvegetable_crisps);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 5));

            //image
            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cosmopolitan);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 6));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cosmopolitan1);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 6));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cosmopolitan2);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 6));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.negroni1);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 7));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.negroni2);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 7));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.negroni3);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 7));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.moscow_mule1);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 8));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.moscow_mule2);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 8));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.moscow_mules3);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 8));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.whiskey_sour1);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 9));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.whiskey_sour2);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 9));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.whiskey_sour3);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 9));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.manhattan1);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 10));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.manhattan2);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 10));

            bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.manhattan3);
            bitmap = bitmapDrawable.getBitmap();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            url = byteArrayOutputStream.toByteArray();
            addNew(new Image(0, url, true, 10));


        }

    }
}
