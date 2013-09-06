package com.example.learnsqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDBHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "productDB.db";
	private static final String TABLE_PRODUCTS = "products";
	
	public static final String COLUMN_ID = "_ID";
	public static final String COLUMN_PRODUCTNAME = "name";
	
	public ProductDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
	             TABLE_PRODUCTS + "("
	             + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME 
	             + " TEXT" + ")";
	      db.execSQL(CREATE_PRODUCTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
	      onCreate(db);
	}
	
	public void addProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductName());
 
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
	}
	
	
	public ArrayList<Product> getAllProduct(){	
		ArrayList<Product> products = new ArrayList<Product>();
		String query = "Select * FROM " + TABLE_PRODUCTS + " ORDER BY _ID ASC";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		int rows_num = cursor.getCount();
		 if(rows_num != 0) {
		  cursor.moveToFirst();   
		  for(int i=0; i<rows_num; i++) {
		   Product product = new Product();
		   product.setID(Integer.parseInt(cursor.getString(0)));
		   product.setProductName(cursor.getString(1));
		   products.add(product);
		   cursor.moveToNext();
		  }
		 }
		 cursor.close();
		
		return products;
	}
	
	public Product findProduct(String productname) {
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Product product = new Product();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			product.setID(Integer.parseInt(cursor.getString(0)));
			product.setProductName(cursor.getString(1));
			cursor.close();
		} else {
			product = null;
		}
	        db.close();
		return product;
	}
	
	// 刪除資料
	public boolean deleteProduct(String productname) {
		
		boolean result = false;
		
		String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Product product = new Product();
		
		if (cursor.moveToFirst()) {
			product.setID(Integer.parseInt(cursor.getString(0)));
			db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
		            new String[] { String.valueOf(product.getID()) });
			cursor.close();
			result = true;
		}
	        db.close();
		return result;
	}
	
}