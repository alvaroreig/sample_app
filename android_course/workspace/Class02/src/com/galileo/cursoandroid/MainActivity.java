package com.galileo.cursoandroid;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {
	private String country = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] arrayCountries = new String[] { "Brasil", "México",
				"Colombia", "Argentina", "Perú", "Venezuela", "Chile",
				"Ecuador", "Guatemala", "Cuba" };

		ArrayList<String> countries = new ArrayList<String>(
				Arrays.asList(arrayCountries));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, countries);

		ListView list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		registerForContextMenu(list);
	}
	
	/*Process click on ListView.*/
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {
		country = adapterView.getItemAtPosition(position).toString();
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			FragmentManager manager = getFragmentManager();
			CountryInfoFragment fragment = (CountryInfoFragment) manager
					.findFragmentById(R.id.fragmentCountryInfo);
			fragment.loadWebViewContent(country);
		} else {
			Intent intent = new Intent(this, CountryDetailActivity.class);
			intent.putExtra(CountryDetailActivity.COUNTRY, country);
			startActivity(intent);
		}
	}
	
	/*Prepare the options menu,only show share button in landscape*/
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean landscape = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
		MenuItem share = menu.getItem(menu.size() - 1);
		share.setVisible(landscape);
		return super.onPrepareOptionsMenu(menu);
	}

	/*Process clic on share/help*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_share:
			if (country != "") {
				String url = "http://es.m.wikipedia.org/wiki/" + country;
				String msg = getString(R.string.msg_share, country, url);
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);
				intent.putExtra(Intent.EXTRA_TEXT, msg);
				intent.setType("text/plain");
				/*
				 * CreateChooser forces the user to choose the app, even if he
				 * already set a default one
				 */
				startActivity(Intent.createChooser(intent,
						getString(R.string.action_share)));
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*Create context menu*/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		country = ((TextView)info.targetView).getText().toString();
		getMenuInflater().inflate(R.menu.main, menu);
	}
	
	
	/*Process clic on context Item. Redirect to options method*/
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return onOptionsItemSelected(item);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}





}
