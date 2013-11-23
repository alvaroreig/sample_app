package com.galileo.cursoandroid.activities;

import com.galileo.cursoandroid.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StoreActivity extends ActionBarActivity {
	public static String TAG_STORE_ACTIVITY = StoreActivity.class.toString();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Set custom title bar and layout */
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_store);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.title_bar);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_picture_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_share:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			String storeName = (String) ((TextView)findViewById(R.id.txtViewName)).getText();
			String storeAddress = (String) ((TextView)findViewById(R.id.txtViewAddress)).getText();
			intent.putExtra(Intent.EXTRA_TEXT, "Check out " + storeName
					+ " at " + storeAddress);
			intent.setType("text/plain");
			startActivity(Intent.createChooser(intent,
					getResources().getText(R.string.action_share)));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * Listener for both buttons in this activity: Call to store and access to
	 * the Photography activity
	 */
}
