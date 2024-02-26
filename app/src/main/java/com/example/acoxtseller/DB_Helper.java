package com.example.acoxtseller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products.db";
    private static final String TABLE_NAME = "products";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PRODUCT_NAME = "product_name";
    private static final String COL_PRICE = "price";
    private static final String COL_DISCOUNT_PRICE = "dis_price";
    private static final String COL_IMAGE = "image";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_QUANTITY = "quantity";
    private static final String COL_TOTAL_PRICE = "total_price";
    private static final String COL_TOTAL_FINAL_PRICE = "total_final_price";

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, 2); // Updated the database version to 2
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_NAME + " TEXT, " +
                COL_PRODUCT_NAME + " TEXT, " +
                COL_PRICE + " REAL, " +
                COL_DISCOUNT_PRICE + " REAL, " +
                COL_IMAGE + " TEXT, " +
                COL_DESCRIPTION + " TEXT, " +
                COL_QUANTITY + " INTEGER, " +
                COL_TOTAL_FINAL_PRICE + "REAL DEFAULT 0, " +
                COL_TOTAL_PRICE + " REAL DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String name,String product_name, double price,double dis_price, String image, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PRODUCT_NAME, product_name);
        contentValues.put(COL_PRICE, price);
        contentValues.put(COL_DISCOUNT_PRICE, dis_price);
        contentValues.put(COL_IMAGE, image);
        contentValues.put(COL_DESCRIPTION, description);
        contentValues.put(COL_QUANTITY, 1);
        contentValues.put(COL_TOTAL_PRICE, price);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;

    }

    public boolean productExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public void incrementQuantityAndPrice(String name, int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String selectQuery = "SELECT " + COL_QUANTITY + ", " + COL_TOTAL_PRICE + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int currentQuantity = cursor.getInt(cursor.getColumnIndex(COL_QUANTITY));
            @SuppressLint("Range") double currentTotalPrice = cursor.getDouble(cursor.getColumnIndex(COL_TOTAL_PRICE));
            cursor.close();

            values.put(COL_QUANTITY, currentQuantity + quantity);
            values.put(COL_TOTAL_PRICE, currentTotalPrice + (price * quantity));

            db.update(TABLE_NAME, values, COL_NAME + " = ?", new String[]{name});
        }
    }


    public void decrementQuantityAndPrice(String name, int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        String selectQuery = "SELECT " + COL_QUANTITY + ", " + COL_TOTAL_PRICE + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int currentQuantity = cursor.getInt(cursor.getColumnIndex(COL_QUANTITY));
            @SuppressLint("Range") double currentTotalPrice = cursor.getDouble(cursor.getColumnIndex(COL_TOTAL_PRICE));
            cursor.close();

            if (currentQuantity >= quantity) {
                values.put(COL_QUANTITY, currentQuantity - quantity);
                values.put(COL_TOTAL_PRICE, currentTotalPrice - (price * quantity));

                // Update the database with the new values
                db.update(TABLE_NAME, values, COL_NAME + " = ?", new String[]{name});

                // Check if the current quantity is now zero and remove the product
                if (currentQuantity - quantity == 0) {
                    db.delete(TABLE_NAME, COL_NAME + " = ?", new String[]{name});
                }
            }
        }
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME,null,null, null, null, null, null);
    }


    public int getProductQuantity(String productName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_QUANTITY + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{productName});

        int quantity = 0;
        if (cursor.moveToFirst()) {
            quantity = cursor.getInt(0);
        }

        cursor.close();
        return quantity;
    }

    public void deleteAllData(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    public void updateTotalPrice() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Retrieve the current total price from the database.
        double currentTotalPrice = 0;
        String query = "SELECT SUM(" + COL_TOTAL_PRICE + ") FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            currentTotalPrice = cursor.getDouble(0);
        }

        // Update the database with the new total price.
        ContentValues values = new ContentValues();
        values.put(COL_TOTAL_FINAL_PRICE, currentTotalPrice);

        db.update(TABLE_NAME, values, null, null);

        // Close the database.
        db.close();
    }
    public double getTotalFinalPrice() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_TOTAL_FINAL_PRICE + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        double totalFinalPrice = 0.0;
        if (cursor.moveToFirst()) {
            totalFinalPrice = cursor.getDouble(0);
        }

        cursor.close();
        return totalFinalPrice;
    }
}