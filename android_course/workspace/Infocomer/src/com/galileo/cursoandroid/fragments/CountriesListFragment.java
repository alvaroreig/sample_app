package com.galileo.cursoandroid.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.StoreActivity;

public class CountriesListFragment extends ListFragment {
	private final static String FRAGMENT_TAG = "CountriesListFragment";
	public final static String STORE_NAME = "name";
	public final static String STORE_ADDRESS = "address";
	public final static String STORE_TELEPHONE = "telephone";
	public final static String STORE_TIME_OPEN = "time_open";
	public final static String STORE_EMAIL = "email";
	public final static String STORE_WEBSITE = "website";
	public final static String STORE_PICTURE = "picture";
	public final static String STORE_COMMENTS = "comments";

	public final static int NUMBER_OF_STORES = 2;
	private List<HashMap<String, String>> stores = new ArrayList<HashMap<String, String>>();

	private HashMap<String, ArrayList<String>> stores_values = new HashMap<String, ArrayList<String>>();

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		/* Link the ViewList with the stores ArrayList */
		Log.i(FRAGMENT_TAG,"1");
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), stores,
				android.R.layout.simple_list_item_1,
				new String[] { STORE_NAME }, new int[] { android.R.id.text1 });
		setListAdapter(adapter);

		Log.i(FRAGMENT_TAG,"2");
		/* Load data from strings.xml */
		populateStores();
		adapter.notifyDataSetChanged();

		Log.i(FRAGMENT_TAG,"3");
		/* Set up an onItemClickListener */
		ListView listView = (ListView) getListView();
		Log.i(FRAGMENT_TAG,"4");
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(getActivity(), StoreActivity.class);
				String storeName = ((TextView) view).getText().toString();
				intent.putExtra(STORE_NAME, storeName);

				ArrayList<String> store_values = stores_values.get(storeName);
				intent.putExtra(STORE_ADDRESS, store_values.get(0));
				intent.putExtra(STORE_TELEPHONE, store_values.get(1));
				intent.putExtra(STORE_TIME_OPEN, store_values.get(2));
				intent.putExtra(STORE_EMAIL, store_values.get(3));
				intent.putExtra(STORE_WEBSITE, store_values.get(4));
				intent.putExtra(STORE_PICTURE, store_values.get(5));
				intent.putExtra(STORE_COMMENTS, store_values.get(6));
				startActivity(intent);
			}
		});
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_countries_list,
				container, false);

		return view;
	}

	/* Method to recover Strings resource using a String instead of an int */
	private String getStringResourceByName(String aString) {
		String packageName = getActivity().getPackageName();
		int resId = getResources()
				.getIdentifier(aString, "string", packageName);
		return getString(resId);
	}

	/*
	 * Load values from string.xml into the arrayList. It also populates the
	 * stores_values HashMap to facilitate the recovery of values for a known
	 * store_name
	 */
	private void populateStores() {
		String name = "";
		String address = "";
		String telephone = "";
		String timeOpen = "";
		String email = "";
		String website = "";
		String picture = "";
		String comments = "";
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
			picture = getStringResourceByName("store_picture_name_"
					+ index_string);
			comments = getStringResourceByName("store_comments_" + index_string);

			/* Populates stores and stores_values */
			store = new HashMap<String, String>();
			store_values = new ArrayList<String>();

			store.put(STORE_NAME, name);
			store.put(STORE_ADDRESS, address);
			store.put(STORE_TELEPHONE, telephone);
			store.put(STORE_TIME_OPEN, timeOpen);
			store.put(STORE_EMAIL, email);
			store.put(STORE_WEBSITE, website);
			store.put(STORE_PICTURE, picture);
			store.put(STORE_COMMENTS, comments);

			store_values.add(address);
			store_values.add(telephone);
			store_values.add(timeOpen);
			store_values.add(email);
			store_values.add(website);
			store_values.add(picture);
			store_values.add(comments);

			stores.add(store);
			stores_values.put(name, store_values);
		}

	}
}