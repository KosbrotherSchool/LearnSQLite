package com.example.learnsqlite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView mainList;
	private ArrayList<Product> mProducts = new ArrayList<Product>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainList = (ListView) findViewById (R.id.main_list);
		
		// 寫入資料
		ProductDBHelper dbHandler = new ProductDBHelper(this, null, null, 1);
		for (int i = 0 ; i< 11 ; i++){	
			Product newProduct = new Product("cup_"+ Integer.toString(i));
			dbHandler.addProduct(newProduct);
		}
		
		// 讀取資料
		mProducts = dbHandler.getAllProduct();
		ListAdapter adapter = new ListAdapter(this, mProducts);
		mainList.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
