package com.galileo.cursoandroid.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.widget.Toast;

import com.galileo.cursoandroid.R;

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
	public final static String TAB_LIST ="Listado";
	public final static String TAB_MAP ="Mapa";

	protected void onCreate(Bundle savedInstanceState) {
		/* Set custom title bar */
		super.onCreate(savedInstanceState);
		// boolean windo=
		// supportRequestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// Log.i("SUPPORTS_CUSTOM_TITLE",Boolean.toString(windo));
		setContentView(R.layout.activity_main);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.TabListener listener = new ActionBar.TabListener() {

			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction arg1) {
				Toast.makeText(getApplicationContext(), tab.getText(),
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub

			}
		};

		actionbar.addTab(actionbar.newTab().setText(TAB_LIST)
				.setTabListener(listener));
		actionbar.addTab(actionbar.newTab().setText(TAB_MAP)
				.setTabListener(listener));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}