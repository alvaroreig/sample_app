package com.galileo.cursoandroid.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.fragments.CountriesListFragment;
import com.galileo.cursoandroid.fragments.MapFragment;
import com.galileo.cursoandroid.fragments.StoresImagesFragment;

/**************************************************/
/* @todo ***************************************** */
/* Centered title */
/* Button instead of icon */

public class MainActivity extends ActionBarActivity {
	public final static String STORE_NAME = "name";
	public final static String STORE_ADDRESS = "address";
	public final static String STORE_TELEPHONE = "telephone";
	public final static String STORE_TIME_OPEN = "time_open";
	public final static String STORE_EMAIL = "email";
	public final static String STORE_WEBSITE = "website";
	public final static String STORE_PICTURE = "picture";
	public final static String STORE_COMMENTS = "comments";

	Fragment[] fragments = new Fragment[] { new CountriesListFragment(),
			new MapFragment(), new StoresImagesFragment() };

	protected void onCreate(Bundle savedInstanceState) {
		/* Set custom title bar */
		super.onCreate(savedInstanceState);
		// supportRequestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// Log.i("SUPPORTS_CUSTOM_TITLE",Boolean.toString(windo));
		setContentView(R.layout.activity_main);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}