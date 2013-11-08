package com.galileo.cursoandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class StoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		Intent intent = getIntent();
		String storeName = intent.getStringExtra(MainActivity.STORE_NAME);
		String storeAddress = intent.getStringExtra(MainActivity.STORE_ADDRESS);
		String storeTelephone = intent.getStringExtra(MainActivity.STORE_TELEPHONE);
		String storeTimeOpen = intent.getStringExtra(MainActivity.STORE_TIME_OPEN);
//		String storeEmail = intent.getStringExtra(MainActivity.STORE_EMAIL);
//		String storeWebsite = intent.getStringExtra(MainActivity.STORE_WEBSITE);
		
		TextView textView = (TextView) findViewById(R.id.txtViewName);
		textView.setText(storeName);
		textView = (TextView) findViewById(R.id.txtViewAddress);
		textView.setText(storeAddress);
		textView = (TextView) findViewById(R.id.txtViewTelephone);
		textView.setText(storeTelephone);
		textView = (TextView) findViewById(R.id.txtViewTImesOpenData);
		textView.setText(storeTimeOpen);
		
		Toast.makeText(getApplicationContext(), storeName, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.store, menu);
		return true;
	}

}
