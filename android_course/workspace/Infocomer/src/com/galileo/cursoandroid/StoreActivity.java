package com.galileo.cursoandroid;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);

		/* Get intent and recover the store values */
		Intent intent = getIntent();
		String storeName = intent.getStringExtra(MainActivity.STORE_NAME);
		String storeAddress = intent.getStringExtra(MainActivity.STORE_ADDRESS);
		String storeTelephone = intent
				.getStringExtra(MainActivity.STORE_TELEPHONE);
		String storeTimeOpen = intent
				.getStringExtra(MainActivity.STORE_TIME_OPEN);

		/* Populate the elements with the recovered values */
		TextView textView = (TextView) findViewById(R.id.txtViewName);
		textView.setText(storeName);
		textView = (TextView) findViewById(R.id.txtViewAddress);
		textView.setText(storeAddress);
		textView = (TextView) findViewById(R.id.txtViewTelephone);
		textView.setText(storeTelephone);
		textView = (TextView) findViewById(R.id.txtViewTImesOpenData);
		textView.setText(storeTimeOpen);

		/* Set up call button */
		Button callButton = (Button) findViewById(R.id.btnCall);
		callButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					Intent callIntent = new Intent(Intent.ACTION_DIAL);
					TextView textViewTelephone = (TextView) findViewById(R.id.txtViewTelephone);
					callIntent.setData(Uri.parse("tel:"
							+ (String) textViewTelephone.getText()));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Log.e("Calling a Phone Number", "Call failed",
							activityException);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.store, menu);
		return true;
	}

}
