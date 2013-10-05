package com.gilpam;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Screen01 extends Activity implements OnClickListener{

	EditText username, password, emailid;
	Button signin, signup;
	dbhandler db = new dbhandler(this);
	String emailidtxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen01);
		
		username = (EditText)findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
		emailid = (EditText)findViewById(R.id.signup_emailid);
		signin = (Button)findViewById(R.id.signinbutton);
		signup = (Button)findViewById(R.id.signupbutton);
		
		signin.setOnClickListener(this);
		signup.setOnClickListener(this);
		
	}
	
	private void newUserSignup(){
//		db.newuserdetail(new user(1,"kiran","duvvuri","kiran@duvvuri.com","consumer","1234567890","12345"));
		emailidtxt = emailid.getText().toString();
		db.newuserquick(new user(emailidtxt,"password"));
		Log.d("Userlog:", "Inserting new user email id as part of quick sign up process with default password");
		Toast.makeText(this, "Signup success with email: " + emailidtxt, Toast.LENGTH_LONG).show();
/*		int numberofusers = db.getusercount();
		Toast.makeText(this, "total user count is "+numberofusers, Toast.LENGTH_LONG).show();*/
	}
	
	private void verifySignin(){
		Log.d("Userlog:","Reading the inserted data...");
		String email = username.getText().toString();
		String pass = password.getText().toString();
		boolean signinflag = db.getuserinfo(email,pass);
		if (signinflag == false){
			Toast.makeText(this, "Username or Password is incorrect, please try again. ", Toast.LENGTH_LONG).show();
			((EditText)findViewById(R.id.login_password)).setText("");
		} else {
			Toast.makeText(this, "Login is a success ", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onClick(View v){
		switch(v.getId())
		{
		case R.id.signinbutton:
			verifySignin();
			break;
		case R.id.signupbutton:
			newUserSignup();
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
