package com.gilpam;

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
	
	private static final int dbversion = 6;
	private static final String dbname = "gilpamdb";
	private static final String tablename = "usertable";

	public dbhandler(Context context) {
		super(context, dbname, null, dbversion);
		
		Log.d("Userlog: ", "you are in constructor dbhandler...");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String SQLCreateUserTable = "CREATE TABLE " + tablename +" ( id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, email TEXT UNIQUE, usertype TEXT, mobilenumber TEXT, password TEXT)";
		Log.d("Userlog: ",SQLCreateUserTable + " executing creation of table");
		db.execSQL(SQLCreateUserTable);
		String fn = "firstname";
		String ln = "lastname";
		String em = "email";
		String ut = "consumer";
		String mn = "1234567890";
		String pw = "password";
		String SQLInsertFirstRecord = "INSERT INTO " + tablename + "(firstname, lastname, email, usertype, mobilenumber, password) VALUES ('" + fn +"','"+ln+"','"+em+"','"+ut+"','"+mn+"','"+pw+ "')";
		Log.d("Userlog: ",SQLInsertFirstRecord + " executing insertion of table");
		db.execSQL(SQLInsertFirstRecord);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		
		if (newversion > oldversion) {
			db.execSQL("DROP TABLE IF EXISTS " + tablename);
			db.execSQL("DROP TABLE IF EXISTS tablename");
			onCreate(db);
		}
		else {
			Log.d("onUpgrade","You are downgrading the version of the app, not possible");
		}
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
		user USER = null;
		boolean returnflag = false;
		int count;
		SQLiteDatabase db = this.getReadableDatabase();
//		Log.d("Userlog: ","getting user info based on email " + emailid);
//		Cursor cursor = db.query(tablename, new String[] {"id","firstname","lastname","email","usertype","mobilenumber","password"},"email = " + emailid,null,null,null,null );
//		Cursor cursor = db.query(tablename, new String[] {"id","firstname","lastname","email","usertype","mobilenumber","password"}, "email = ?",new String[] { String.valueOf(emailid) },null,null,null,null );
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
//			curso.moveToFirst();
//			Log.d("Userlog","count is " + curso.getCount());
//			USER = new user (Integer.parseInt(curso.getString(0)),curso.getString(1),curso.getString(2),curso.getString(3),curso.getString(4),curso.getString(5),curso.getString(6));
//	        curso.close();
		}
		else {
			Log.d("Userlog" , "cursor is either null or is closed");
			return false;
		}
		
//		if (cursor != null) {
//			cursor.moveToFirst();
//			 USER = new user (Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
//		}
	}

}
