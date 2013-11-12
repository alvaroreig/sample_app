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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String []arrayCountries = new String[]{
				"Brasil","México","Colombia",
				"Argentina","Perú","Venezuela",
				"Chile","Ecuador","Guatemala","Cuba"
		};
		
		ArrayList<String> countries = new ArrayList<String>(Arrays.asList(arrayCountries));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				countries);
		
		ListView list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		String country = adapterView.getItemAtPosition(position).toString();
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			FragmentManager manager = getFragmentManager();
			CountryInfoFragment fragment = (CountryInfoFragment) manager.findFragmentById(R.id.fragmentCountryInfo);
			fragment.loadWebViewContent(country);
		}else{
			Intent intent = new Intent(this,CountryDetailActivity.class);
			intent.putExtra(CountryDetailActivity.COUNTRY, country);
			startActivity(intent);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean landscape= (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
			MenuItem share = menu.getItem(menu.size()-1);
			share.setVisible(landscape);
			return super.onPrepareOptionsMenu(menu);
	}
	
	

}
