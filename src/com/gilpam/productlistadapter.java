package com.gilpam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class productlistadapter extends BaseAdapter {
	private ArrayList<Product> product;
	Context context;

	productlistadapter (ArrayList<Product> product, Context context){
		this.product = product;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return product.size();
	}

	@Override
	public Object getItem(int position) {
		return product.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.productlistview, null);
		}
		
		ImageView productimage = (ImageView)v.findViewById(R.id.productimage);
		TextView productname = (TextView)v.findViewById(R.id.productname);
		TextView productuom = (TextView)v.findViewById(R.id.productuom);
		TextView productprice = (TextView)v.findViewById(R.id.productprice);
		
		Product PRODUCT = product.get(position);
		
		productimage.setImageResource(PRODUCT.image);
		productname.setText(PRODUCT.name);
		productuom.setText("UOM: "+PRODUCT.uom);
		productprice.setText("Rs." +PRODUCT.price);
		
		return v;
	}

}
