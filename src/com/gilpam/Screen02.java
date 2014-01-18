
package com.gilpam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class Screen02 extends Activity {
	
	Intent intent;
	String username,Note;
	public final static String EXTRA_MESSAGE = "com.gilpam.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen02);
		final Intent intent = getIntent();
		final String username = intent.getStringExtra(Screen01.EXTRA_MESSAGE);
		Note = "Welcome " + username;
		((TextView)findViewById(R.id.LognSuccessNote)).setText(Note);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen01, menu);
		return true;
	}

}
