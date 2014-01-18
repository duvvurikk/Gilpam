package com.gilpam;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class dbhandler extends SQLiteOpenHelper {
	
	private static final int dbversion = 8;
	private static final String dbname = "gilpamdb";
	private static final String tablename = "usertable";

	public dbhandler(Context context) {
		super(context, dbname, null, dbversion);
		
		Log.d("Userlog: ", "you are in constructor dbhandler...");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String SQLCreateUserTable = "CREATE TABLE " + tablename +" ( id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, email TEXT UNIQUE, usertype TEXT, mobilenumber TEXT, password TEXT)";
		db.execSQL(SQLCreateUserTable);
		Log.d("Userlog: ",SQLCreateUserTable + " executing creation of user table");
		
		
		String SQLCreateProductTable = "CREATE TABLE producttable (productid INTEGER PRIMARY KEY AUTOINCREMENT, image INTEGER, name TEXT, brand TEXT, mfg TEXT, category1 TEXT, category2 TEXT, category3 TEXT, uom TEXT, price REAL, discount INTEGER, retailunit TEXT,barcode TEXT)";
		db.execSQL(SQLCreateProductTable);
		Log.d("Userlog: ",SQLCreateProductTable + " executing creation of product table");
		
		
		insertdefaultuser(db);
		insertdefaultproductlist(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		
		if (newversion > oldversion) {
			Log.d("Userlog","you have upgraded the version executing drop command");
			db.execSQL("DROP TABLE IF EXISTS " + tablename);
			db.execSQL("DROP TABLE IF EXISTS tablename");
			db.execSQL("DROP TABLE IF EXISTS producttable");
			Log.d("userlog","dropped both the tables, now calling create:" + tablename);
			onCreate(db);
		}
		else {
			Log.d("onUpgrade","You are downgrading the version of the app, not possible");
		}
	}
	
	void insertdefaultuser(SQLiteDatabase db){
		Log.d("Userlog","this is inserting default user");
//		SQLiteDatabase db = this.getWritableDatabase();
		Log.d("Userlog","but before attributes");
		String fn = "firstname";
		String ln = "lastname";
		String em = "email";
		String ut = "consumer";
		String mn = "1234567890";
		String pw = "password";
		String SQLInsertFirstRecord = "INSERT INTO " + tablename + "(firstname, lastname, email, usertype, mobilenumber, password) VALUES ('" + fn +"','"+ln+"','"+em+"','"+ut+"','"+mn+"','"+pw+ "')";
		Log.d("Userlog: ",SQLInsertFirstRecord + " executing insertion of table");
		db.execSQL(SQLInsertFirstRecord);
//		db.close();
	}
	void insertdefaultproductlist(SQLiteDatabase db){
//		SQLiteDatabase db = this.getWritableDatabase();
		String SQLInsertProducts = null;
		 SQLInsertProducts = "INSERT INTO producttable (name,brand,mfg,category1,category2,category3,uom,price,discount,retailunit,barcode) VALUES ('Happydent White xylit Bottle 27.5g','Happydent','PERFETTI VAN MELLE INDIA PVT. LTD','Confectionary','Chewing gum','Chocolates','1 bottle',30,0,'Food Bazar','8901393002867')";
		db.execSQL(SQLInsertProducts);
		Log.d("Userlog","SQLInsertProduct 1 executed successfully");
		 SQLInsertProducts = "INSERT INTO producttable (name,brand,mfg,category1,category2,category3,uom,price,discount,retailunit,barcode) VALUES ('Kissan Tomato Puree 200g','Kissan','Hindustan Unilever Ltd','Grocery & Staples','Cooking Aids','Spreads & Sauces','1 pack',20,0,'Food Bazar','8901030387982')";
		db.execSQL(SQLInsertProducts);
		Log.d("Userlog","SQLInsertProduct 2 executed successfully");
		 SQLInsertProducts = "INSERT INTO producttable (name,brand,mfg,category1,category2,category3,uom,price,discount,retailunit,barcode) VALUES ('Odonil multi freshner','Odonil','Dabur India Ltd','Household','Freshners','Cleaning','1 pack of 4',116,0,'More Supermarket','8901207014871')";
		db.execSQL(SQLInsertProducts);
		Log.d("Userlog","SQLInsertProduct 3 executed successfully");
		 SQLInsertProducts = "INSERT INTO producttable (name,brand,mfg,category1,category2,category3,uom,price,discount,retailunit,barcode) VALUES ('Britannia Treat ORANGE 75g','Britannia','Britannia Industries Ltd','Confectionary','Biscuits','','1 pack',12,0,'More Supermarket','8901063031555')";
		db.execSQL(SQLInsertProducts);
		Log.d("Userlog","SQLInsertProduct 4 executed successfully");
		 SQLInsertProducts = "INSERT INTO producttable (name,brand,mfg,category1,category2,category3,uom,price,discount,retailunit,barcode) VALUES ('Lifebuoy Total Handwash 215ml','Lifebuoy','Hindustan Unilever Ltd','Personal Care','Liquid Soaps','','1 bottle',50,0,'Kirana Shop','8901030394478')";
		db.execSQL(SQLInsertProducts);
		Log.d("Userlog","SQLInsertProduct 5 executed successfully");
		SQLInsertProducts = "INSERT INTO producttable (name,brand,mfg,category1,category2,category3,uom,price,discount,retailunit,barcode) VALUES ('Dettol Total Handwash 215ml','Lifebuoy','Hindustan Unilever Ltd','Personal Care','Liquid Soaps','','1 bottle',50,0,'Kirana Shop','8901030394478')";
		db.execSQL(SQLInsertProducts);
		Log.d("Userlog","SQLInsertProduct 5 executed successfully");
//		db.close();
	}
	
	 void newuserquick(user newuser){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("email",newuser.getemail());
		values.put("password", newuser.getpassword());
		try {
			db.insertOrThrow(tablename, null, values);
		} catch (SQLException e){
			Log.d("Userlog","newuserquick db inserting error message: " +e.getMessage());
		}
		try {
			db.close();
		} catch (SQLException e){
			Log.d("Userlog","newuserquick db close error: " +e.toString());
		}
	}
	
	 void newuserdetail(user newuser){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("firstname", newuser.getfirstname());
		values.put("lastname", newuser.getlastname());
		values.put("mobilenumber",newuser.getmobilenumber());
		values.put("password", newuser.getpassword());
		values.put("usertype", newuser.getusertype());
		String whereclause = "email='"+newuser.getemail()+"'";
		try {
			db.update(tablename, values, whereclause, null);
		} catch (SQLException e){
			Log.d("Userlog","new user detail db inserting error message: " + e.getMessage());
			getuserinfo(newuser.getemail());
		}
		try {
			db.close();
		} catch (SQLException e){
			Log.d("Userlog","new user detail db close error message: " + e.getMessage());
		}
		
	}
	
	public int getusercount(){
		int count = 0;
		SQLiteDatabase db = this.getReadableDatabase();
		String SQL = "SELECT * FROM " + tablename + " WHERE email = 'KIRAN'";
		Log.d("Userlog","sequel is " + SQL);
		Cursor cursor = db.rawQuery(SQL, null);
		if(cursor != null && !cursor.isClosed()){
			count = cursor.getCount();
	        cursor.close();
	        Log.d("Userlog","count of records in database are " + count);
		}
		else {
			Log.d("Userlog" , "cursor is either null or is closed");
		}
		return count;	
	}
	
	public void getuserinfo(String email){
		user USER = null;
		SQLiteDatabase db = this.getReadableDatabase();
		Log.d("Userlog: ", "opened the database in readonly mode");
//		Cursor cursor = db.query(tablename, new String[] {"id","firstname","lastname","email","usertype","mobilenumber","password"},"id = ? ", new String[] {String.valueOf(id)},null,null,null,null );
		Cursor cursor = db.query(tablename, new String[] {"id","firstname","lastname","email","usertype","mobilenumber","password"},"email = '" + email + "'",null,null,null,null );
		if (cursor != null) {
			cursor.moveToFirst();
			String pass = cursor.getString(6);
			Log.d("Userlog","query resulted in password: "+ pass + " end of line");
//			 USER = new user (Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
		}
//		return USER;
	} 
	
	boolean getuserinfo(String emailid, String password){
//		user USER = null;
//		boolean returnflag = false;
		int count;
		SQLiteDatabase db = this.getReadableDatabase();
		String SQL = "SELECT * FROM " + tablename + " where email = '" + emailid +"' and password = '" + password + "'";
		Log.d("Userlog","sequel is " + SQL);
			Cursor curso = db.rawQuery(SQL, null);
			count = curso.getCount();
			Log.d("Userlog","count of records before if is " + count);
		if(curso != null && !curso.isClosed()){
			count = curso.getCount();
			if (count  == 1){
				curso.close();
				Log.d("Userlog","count of records in if is " + count);
				return true;
			}else {
				curso.close();
				Log.d("Userlog","you are in else part for count " + count);
				return false;
			}
		}
		else {
			Log.d("Userlog" , "cursor is either null or is closed");
			return false;
		}
		
	}
	
	int getDefaultProductlistCount(){
		int count;
		SQLiteDatabase db = this.getReadableDatabase();
		String SQL = "SELECT * FROM producttable";
		Cursor curso = db.rawQuery(SQL, null);
		count = curso.getCount();
		return count;
	}
	
	Product getProduct(int pdcusoposition){
		int count;
		int productid;
		int image;
		String name;
		String brand;
		String mfg;
		String category1;
		String category2;
		String category3;
		String uom;
		double price;
		int discount;
		String retailunit;
		String barcode;
		ArrayList<Product> productarray;
		productarray = new ArrayList<Product>();
		Product product = null;
		SQLiteDatabase db = this.getReadableDatabase();
		String SQL = "SELECT * FROM producttable";
		Log.d("Userlog","sequel for product list is " + SQL);
		Cursor curso = db.rawQuery(SQL, null);
		count = curso.getCount();
		Log.d("Userlog","count of products in the table is " + count);
		curso.moveToPosition(pdcusoposition);
		Log.d("Userlog","cursor position is "+ curso.getPosition());
		product = new Product();
		productid = curso.getInt(0);
		product.setproductid(productid);
		image = curso.getInt(1);
		product.setimage(image);
		name = curso.getString(2);
		product.setname(name);
		brand = curso.getString(3);
		product.setbrand(brand);
		mfg = curso.getString(4);
		product.setmanufacturar(mfg);
		category1 = curso.getString(5);
		product.setcategory1(category1);
		category2 = curso.getString(6);
		product.setcategory2(category2);
		category3 = curso.getString(7);
		product.setcategory3(category3);
		uom = curso.getString(8);
		product.setuom(uom);
		price = curso.getDouble(9);
		product.setprice(price);
		discount = curso.getInt(10);
		product.setdiscount(discount);
		retailunit = curso.getString(11);
		product.setretailunit(retailunit);
		barcode = curso.getString(12);
		product.setbarcode(barcode);
		Log.d("Userlog","product id is " + productid + "and its name is " + name);
		
		return product;
	}

}
