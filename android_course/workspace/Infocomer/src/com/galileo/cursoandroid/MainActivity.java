package com.galileo.cursoandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private final static String MAIN_TAG = "MainActivity";
	private final static String STORE_NAME = "name";
	private final static String STORE_ADDRESS = "address";
	private final static String STORE_TELEPHONE = "telephone";
	private final static String STORE_TIME_OPEN = "time_open";
	private final static String STORE_EMAIL = "email";
	private final static String STORE_WEBSITE = "website";
	private List<HashMap<String, String>> stores = new ArrayList<HashMap<String, String>>();
	public final static int NUMBER_OF_STORES = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		populateStores();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String getStringResourceByName(String aString) {
		String packageName = getPackageName();
		int resId = getResources()
				.getIdentifier(aString, "string", packageName);
		return getString(resId);
	}
	
	private void populateStores(){
		String name = "";
		String address = "";
		String telephone = "";
		String timeOpen = "";
		String email = "";
		String website = "";
		HashMap<String,String> store;
		
		// Recover stores from strings.xml
		String index_string="0";
		for (int i = 1; i <= NUMBER_OF_STORES; i++) {
			index_string = Integer.toString(i);
			name = getStringResourceByName("store_name_" + index_string);
			address = getStringResourceByName("store_address_"
					+ index_string);
			telephone = getStringResourceByName("store_telephone_"
					+ index_string);
			timeOpen = getStringResourceByName("store_time_open_"
					+ index_string);
			email = getStringResourceByName("store_email_"
					+ index_string);
			website = getStringResourceByName("store_website_"
					+ index_string);
			
			Log.i(MAIN_TAG, "name of store number " + index_string +" " + name);
			Log.i(MAIN_TAG, "address of store number " + index_string +" " + address);
			Log.i(MAIN_TAG, "telephone of store number " + index_string +" " + telephone);
			Log.i(MAIN_TAG, "timeOpen of store number " + index_string +" " + timeOpen);
			Log.i(MAIN_TAG, "email of store number " + index_string +" " + email);
			Log.i(MAIN_TAG, "website of store number " + index_string +" " + website);
			
			store = new HashMap<String,String>();
			store.put(STORE_NAME, name);
			store.put(STORE_ADDRESS, address);
			store.put(STORE_TELEPHONE, telephone);
			store.put(STORE_TIME_OPEN, timeOpen);
			store.put(STORE_EMAIL, email);
			store.put(STORE_WEBSITE, website);
			
			stores.add(store);
		}

		
		
	}

}
