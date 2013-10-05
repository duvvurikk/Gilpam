package com.gilpam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen03 extends Activity implements OnClickListener{
	
	dbhandler db = new dbhandler(this);
	String firstname, lastname, email, mobilenumber, password;
	Button saveprofile, clearprofile, skipprofile;
	Intent intent;
	String username,Note;
	public final static String EXTRA_MESSAGE = "com.gilpam.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen03);
		final Intent intent = getIntent();
		final String username = intent.getStringExtra(Screen01.EXTRA_MESSAGE);
		Note = "Thanks for signing up " + username;
		((TextView)findViewById(R.id.SignupSuccessNote)).setText(Note);
		((EditText)findViewById(R.id.email)).setText(username);
		
		saveprofile = (Button)findViewById(R.id.saveprofile);
		clearprofile = (Button)findViewById(R.id.clearprofile);
		skipprofile = (Button)findViewById(R.id.skipprofile);
				
		saveprofile.setOnClickListener(this);
		clearprofile.setOnClickListener(this);
		skipprofile.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v){
		
		switch(v.getId()){
		case R.id.saveprofile:
			firstname = ((EditText)findViewById(R.id.firstname)).getText().toString();
			lastname = ((EditText)findViewById(R.id.lastname)).getText().toString();
			email = ((EditText)findViewById(R.id.email)).getText().toString();
			mobilenumber = ((EditText)findViewById(R.id.mobile)).getText().toString();
			password = ((EditText)findViewById(R.id.password)).getText().toString();
			db.newuserdetail(new user(firstname,lastname,email,"consumer",mobilenumber,password));
			Toast.makeText(this, "Profile successfully saved", Toast.LENGTH_LONG).show();
			break;
		case R.id.clearprofile:
			((EditText)findViewById(R.id.firstname)).setText("");
			((EditText)findViewById(R.id.lastname)).setText("");
			((EditText)findViewById(R.id.mobile)).setText("");
			((EditText)findViewById(R.id.password)).setText("");
			break;
		case R.id.skipprofile:
			Toast.makeText(this, "you are skipping the profile setup", Toast.LENGTH_LONG).show();
			intent = new Intent (this,Screen02.class);
			email = ((EditText)findViewById(R.id.email)).getText().toString();
			intent.putExtra(EXTRA_MESSAGE, email);
			startActivity(intent);
			break;
		default:
			Toast.makeText(this, "this is default switch ", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen01, menu);
		return true;
	}

}
