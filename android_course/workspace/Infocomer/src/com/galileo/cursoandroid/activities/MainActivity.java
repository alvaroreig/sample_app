package com.galileo.cursoandroid.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.fragments.CommunityFragment;
import com.galileo.cursoandroid.fragments.StoresImagesFragment;
import com.galileo.cursoandroid.fragments.StoresListFragment;
import com.galileo.cursoandroid.fragments.StoresMainFragment;

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
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private String[] drawerOptions;
	private Fragment[] fragments = new Fragment[] { new StoresMainFragment(),
			new StoresImagesFragment(), new CommunityFragment() };

	protected void onCreate(Bundle savedInstanceState) {
		/* Set custom title bar */
		super.onCreate(savedInstanceState);
		// supportRequestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// Log.i("SUPPORTS_CUSTOM_TITLE",Boolean.toString(windo));
		setContentView(R.layout.activity_main);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

		/* Init drawer */
		drawerList = (ListView) findViewById(R.id.leftDrawer);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerOptions = getResources().getStringArray(R.array.drawer_options);

		drawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, drawerOptions));

		drawerList.setItemChecked(0, true);
		drawerList.setOnItemClickListener(new DrawerItemClicListener());

		/* Add fragments */
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().add(R.id.contentFrame, fragments[0])
				.add(R.id.contentFrame, fragments[1]).commit();
		
		/*Set up TAB mode*/
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		setContent(0);
		actionBar.setTitle(drawerOptions[0]);
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setContent(int index) {
		Fragment toHide = null;
		Fragment toShow = null;
		Fragment toHideTwo = null;
		ActionBar actionBar = getSupportActionBar();

		switch (index) {
		case 0:
			toShow = fragments[0];
			toHide = fragments[1];
			toHideTwo = fragments[2];
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			break;
		case 1:
			toHide = fragments[0];
			toShow = fragments[1];
			toHideTwo = fragments[2];
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		case 2:
			toHide = fragments[0];
			toHideTwo = fragments[1];
			toShow = fragments[2];
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		}
		
		actionBar.setTitle(drawerOptions[index]);
		FragmentManager manager = getSupportFragmentManager();

		manager.beginTransaction().hide(toHide).hide(toHideTwo).show(toShow)
				.commit();
		drawerList.setItemChecked(index,true);
		drawerLayout.closeDrawer(drawerList);
	}

	private class DrawerItemClicListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			setContent(position);

		}

	}

}