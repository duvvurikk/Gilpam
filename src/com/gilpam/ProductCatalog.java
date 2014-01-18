/*
 * This screen pops up after success ful login and item list is displayed *
 */

package com.gilpam;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;

public class ProductCatalog extends Activity {
	dbhandler db = new dbhandler(this);
	ListView productcataloglistview;
	ArrayList<Product> productarray;
	AdapterView.AdapterContextMenuInfo info;
	Intent intent;
	String username;
	public final static String EXTRA_MESSAGE = "com.gilpam.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_catalog);
		
		final Intent intent = getIntent();
		final String username = intent.getStringExtra(Screen01.EXTRA_MESSAGE);
		
		productcataloglistview = (ListView)findViewById(R.id.productcataloglistview);
		productarray = new ArrayList<Product>();
		Product product;	
		int productcount = db.getDefaultProductlistCount();
		int pdcusoposition;
		for (pdcusoposition=0;pdcusoposition<productcount;pdcusoposition++){
			product = db.getProduct(pdcusoposition);
			productarray.add(product);
		}
		Log.d("Userlog","product array received after the call to function ");
		
		productcataloglistview.setAdapter(new productlistadapter(productarray,this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_catalog, menu);
		return true;
	}

}
