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
            addNew(new Product(0, "Cosmopolitan", 5.0, "The cosmo became almost ubiquitous in the '90s thanks to the TV show Sex and the City, but this spin on the martini remains just as tasty today as when Carrie Bradshaw made it famous.", 4.5F, 15, true, 5));
            addNew(new Product(0, "Negroni", 6.0, "A certain Noel Negroni disputes the fact that Count Camillo Negroni created the drink, citing a relative of his, General Pascal Olivier Count de Negroni, as being the man responsible for coming up with the recipe.", 4.5F, 20, true, 5));
            addNew(new Product(0, "Moscow Mule", 9.0, "Popular for good reason, the Moscow Mule is one of the most refreshing things to sip on a hot summer day. Its suggested vessel, a copper mug, also just looks sharp.", 4.8F, 30, true, 5));
            addNew(new Product(0, "Whiskey Sour", 8.0, "Perhaps the most refreshing whiskey cocktail, this is an old reliable favorite.", 4.7F, 20, true, 5));
            addNew(new Product(0, "Manhattan", 8.0, "Created sometime in the mid-1800s, the Manhattan is one of the booziest classic drink recipes.", 4.6F, 15, true, 5));

            addNew(new Product(0, "_chocolate Cake", 10, "Rich in chocalate flavour with a tender-moist crumb", 4.0F, 7, true, 4));

            addNew(new Product(0, "_cherrypie", 8, " Plus it tastes pretty awesome no matter which flavor is on the menu", 4.0F, 8, true, 4));

            addNew(new Product(0, "_strawberry Cake", 8, " This strawberry cake completely blew me away. After years of mediocre from-scratch strawberry cakes, my expectations were pretty low", 4.5F, 5, true, 4));

            addNew(new Product(0, "_doraemon Cake", 7, " Doraemon has been the most loved animation character for each kid for quite a while now. So think about their reaction when they see their most loved animation character on their birthday cake", 3.5F, 7, true, 4));

            addNew(new Product(0, "_flan", 6, " a dessert of sweetened egg custard with a caramel topping. an open, tartlike pastry, the shell of which is baked in a bottomless band of metal (flan ring ) on a baking sheet", 5.0F, 10, true, 4));



            addNew(new Product(0," Vegetarian hot pot ", 20, "During family reunions or weekend meals, hot pot is always the first choice of housewives. But if you are fed up with seafood or meat, then vegetarian hot pot is a great suggestion for you. Therefore, Cet.edu.vn will guide you how to cook a spicy vegetarian hot pot and a frugal vegetarian mushroom hotpot!", 4.0F, 22, true, 1));
            addNew(new Product(0, "Crab hotpot", 10, " Crab hotpot has long been considered a delicious dish in the hot pot of Vietnam and is popular with everyone and cannot be ignored by the characteristic delicious taste of many ingredients mixed together. No matter how it is combined with the ingredients or the variation of how to cook the Hanoi crab hotpot, the dish always has a sweet, fragrant flavor characteristic of crab and popular vegetables. ", 4.0F, 11, true, 1));
            addNew(new Product(0, " Seafood hot pot", 9, "Seafood hot pot is a favorite dish in cold days. In which, spicy seafood hotpot and mixed seafood hotpot are the two most popular types. However, not everyone knows to have a pot of hot pot broth.", 4.0F, 10, true, 1));
            addNew(new Product(0, "Mam hotpot", 10,"Mam hotpot is a delicious and attractive dish of the West River region. With a rich flavor from a combination of rustic ingredients such as fish sauce, fish, squid, meat, eggplant, and vegetables, along with how to cook fish sauce hotpot that DTBTAAu shares below promises a family menu There will be more fun.", 4.0F, 11, true, 2));
            addNew(new Product(0, "Thai Hot pot", 10, " The way to cook Thai hot pot with the sour, spicy taste of the Golden Temple country is extremely simple without seasoning packages. Fully prepared the ingredients of seafood, fish meat, vegetables and follow the instructions that everyone can sit around the hot pot to enjoy and talk. Not only convenient, economical and hygienic, but the Thai hotpot also has a delicious and attractive taste, not less than the restaurant.", 4.0F, 11, true, 2));
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
