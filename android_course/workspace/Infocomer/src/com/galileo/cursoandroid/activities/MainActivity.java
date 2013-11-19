package com.galileo.cursoandroid.activities;

import com.galileo.cursoandroid.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

/**************************************************/
/* @todo ***************************************** */
/* Centered title */
/* Button instead of icon */

public class MainActivity extends ActionBarActivity{
	public final static String STORE_NAME = "name";
	public final static String STORE_ADDRESS = "address";
	public final static String STORE_TELEPHONE = "telephone";
	public final static String STORE_TIME_OPEN = "time_open";
	public final static String STORE_EMAIL = "email";
	public final static String STORE_WEBSITE = "website";
	public final static String STORE_PICTURE = "picture";
	public final static String STORE_COMMENTS = "comments";


	protected void onCreate(Bundle savedInstanceState) {
		/* Set custom title bar */
		super.onCreate(savedInstanceState);
//		boolean windo= supportRequestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		Log.i("SUPPORTS_CUSTOM_TITLE",Boolean.toString(windo));
		setContentView(R.layout.activity_main);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}