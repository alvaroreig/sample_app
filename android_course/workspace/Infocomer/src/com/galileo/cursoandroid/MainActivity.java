package com.galileo.cursoandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**************************************************/
/* @todo ***************************************** */
/* Centered title */
/* Button instead of icon */

public class MainActivity extends ListActivity {
	private final static String MAIN_TAG = "MainActivity";
	public final static String STORE_NAME = "name";
	public final static String STORE_ADDRESS = "address";
	public final static String STORE_TELEPHONE = "telephone";
	public final static String STORE_TIME_OPEN = "time_open";
	public final static String STORE_EMAIL = "email";
	public final static String STORE_WEBSITE = "website";
	
	public final static int NUMBER_OF_STORES = 2;
	private List<HashMap<String, String>> stores = new ArrayList<HashMap<String, String>>();
	
	/*HashMap to recover efficiently the values associated to a given store name.*/
	private HashMap <String,ArrayList<String>> stores_values = new HashMap <String,ArrayList<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

		/*Link the ViewList with the stores ArrayList*/
		SimpleAdapter adapter = new SimpleAdapter(this, stores,
				android.R.layout.simple_list_item_1,
				new String[] { STORE_NAME }, new int[] { android.R.id.text1 });
		setListAdapter(adapter);

		/*Load data from strings.xml*/
		populateStores();
		adapter.notifyDataSetChanged();

		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(getApplicationContext(),StoreActivity.class);
				String storeName = ((TextView) view).getText().toString();
				intent.putExtra(STORE_NAME,storeName);
				
				ArrayList<String> store_values = stores_values.get(storeName);
				intent.putExtra(STORE_ADDRESS,store_values.get(0));
				intent.putExtra(STORE_TELEPHONE,store_values.get(1));
				intent.putExtra(STORE_TIME_OPEN,store_values.get(2));
				intent.putExtra(STORE_EMAIL,store_values.get(3));
				intent.putExtra(STORE_WEBSITE,store_values.get(4));
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* Method to recover Strings resource using a String instead of an int */
	private String getStringResourceByName(String aString) {
		String packageName = getPackageName();
		int resId = getResources()
				.getIdentifier(aString, "string", packageName);
		return getString(resId);
	}

	/*Load values from string.xml into the arrayList. It also populates the stores_values HashMap
	 * to facilitate the recovery of values for a known store_name*/
	private void populateStores() {
		String name = "";
		String address = "";
		String telephone = "";
		String timeOpen = "";
		String email = "";
		String website = "";
		HashMap<String, String> store;
		ArrayList<String> store_values;

		// Recover stores from strings.xml
		String index_string = "0";
		for (int i = 1; i <= NUMBER_OF_STORES; i++) {
			index_string = Integer.toString(i);
			name = getStringResourceByName("store_name_" + index_string);
			address = getStringResourceByName("store_address_" + index_string);
			telephone = getStringResourceByName("store_telephone_"
					+ index_string);
			timeOpen = getStringResourceByName("store_time_open_"
					+ index_string);
			email = getStringResourceByName("store_email_" + index_string);
			website = getStringResourceByName("store_website_" + index_string);

			Log.i(MAIN_TAG, "name of store number " + index_string + " " + name);
			Log.i(MAIN_TAG, "address of store number " + index_string + " "
					+ address);
			Log.i(MAIN_TAG, "telephone of store number " + index_string + " "
					+ telephone);
			Log.i(MAIN_TAG, "timeOpen of store number " + index_string + " "
					+ timeOpen);
			Log.i(MAIN_TAG, "email of store number " + index_string + " "
					+ email);
			Log.i(MAIN_TAG, "website of store number " + index_string + " "
					+ website);

			
			/*Populates stores and stores_values */
			store = new HashMap<String, String>();
			store_values = new ArrayList<String>();
			
			store.put(STORE_NAME, name);
			store.put(STORE_ADDRESS, address);
			store.put(STORE_TELEPHONE, telephone);
			store.put(STORE_TIME_OPEN, timeOpen);
			store.put(STORE_EMAIL, email);
			store.put(STORE_WEBSITE, website);
			
			store_values.add(address);
			store_values.add(telephone);
			store_values.add(timeOpen);
			store_values.add(email);
			store_values.add(website);

			stores.add(store);
			stores_values.put(name, store_values);
		}

	}

}
