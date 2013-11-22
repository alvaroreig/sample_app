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

/**************************************************/
/* @todo ***************************************** */
/* Centered title */
/* Button instead of icon */

public class MainActivity extends ActionBarActivity implements TabListener {
	public final static String STORE_NAME = "name";
	public final static String STORE_ADDRESS = "address";
	public final static String STORE_TELEPHONE = "telephone";
	public final static String STORE_TIME_OPEN = "time_open";
	public final static String STORE_EMAIL = "email";
	public final static String STORE_WEBSITE = "website";
	public final static String STORE_PICTURE = "picture";
	public final static String STORE_COMMENTS = "comments";

	Fragment[] fragments = new Fragment[] { new CountriesListFragment(),
			new MapFragment() };

	protected void onCreate(Bundle savedInstanceState) {
		/* Set custom title bar */
		super.onCreate(savedInstanceState);
		// supportRequestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// Log.i("SUPPORTS_CUSTOM_TITLE",Boolean.toString(windo));
		setContentView(R.layout.activity_main);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().add(R.id.mainContent, fragments[0])
				.add(R.id.mainContent, fragments[1]).commit();

		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionbar.addTab(actionbar.newTab()
				.setText(R.string.fragment_title_list).setTabListener(this));
		actionbar.addTab(actionbar.newTab()
				.setText(R.string.fragment_title_map).setTabListener(this));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		setContent(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public void setContent(int tab) {
		Fragment toHide = null;
		Fragment toShow = null;
		switch (tab) {
		case 0:
			toHide = fragments[1];
			toShow = fragments[0];
			break;
		case 1:
			toHide = fragments[0];
			toShow = fragments[1];
			break;
		}

		FragmentManager manager = getSupportFragmentManager();

		manager.beginTransaction().hide(toHide).show(toShow).commit();
	}
}