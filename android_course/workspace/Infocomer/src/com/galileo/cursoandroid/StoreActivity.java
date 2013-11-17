package com.galileo.cursoandroid;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StoreActivity extends ActionBarActivity {
	public static String TAG_STORE_ACTIVITY = StoreActivity.class.toString();
	private String storeName;
	private String storeAddress;
	private String storeTelephone;
	private String storeTimeOpen;
	private String storeEmail;
	private String storeWebsite;
	private String storePicture;
	private String storeComments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Set custom title bar and layout */
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_store);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.title_bar);

		/* Get intent and recover the store values */
		Intent intent = getIntent();
		storeName = intent.getStringExtra(MainActivity.STORE_NAME);
		storeAddress = intent.getStringExtra(MainActivity.STORE_ADDRESS);
		storeTelephone = intent.getStringExtra(MainActivity.STORE_TELEPHONE);
		storeTimeOpen = intent.getStringExtra(MainActivity.STORE_TIME_OPEN);
		storeEmail = intent.getStringExtra(MainActivity.STORE_EMAIL);
		storeWebsite = intent.getStringExtra(MainActivity.STORE_WEBSITE);
		storePicture = intent.getStringExtra(MainActivity.STORE_PICTURE);
		storeComments = intent.getStringExtra(MainActivity.STORE_COMMENTS);

		/* Populate the elements with the recovered values */
		TextView textView = (TextView) findViewById(R.id.txtViewName);
		textView.setText(storeName);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) findViewById(R.id.txtViewAddress);
		textView.setText(storeAddress);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) findViewById(R.id.txtViewTelephone);
		textView.setText(storeTelephone);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) findViewById(R.id.txtViewEmail);
		textView.setText(storeEmail);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) findViewById(R.id.txtViewWebsite);
		textView.setText(storeWebsite);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) findViewById(R.id.txtViewTImesOpenData);
		textView.setText(storeTimeOpen);
		Linkify.addLinks(textView, Linkify.ALL);

		/* Set up picture button */
		Button pictureButton = (Button) findViewById(R.id.btnPicture);
		PictureOrCallListener listener = new PictureOrCallListener(
				storePicture, storeComments);
		pictureButton.setOnClickListener(listener);

		/* Set up call button */
		Button callButton = (Button) findViewById(R.id.btnCall);
		callButton.setOnClickListener(listener);
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
	private class PictureOrCallListener implements OnClickListener {
		String pictureName;
		String comments;

		public PictureOrCallListener(String pictureName, String comments) {
			super();
			this.pictureName = pictureName;
			this.comments = comments;
		}

		public void onClick(View v) {
			if (v.getId() == ((Button) findViewById(R.id.btnPicture)).getId()) {
				/*
				 * Add picture name and comments to the intent and open
				 * PhotographyDetailActivity
				 */
				Intent intent = new Intent(getApplicationContext(),
						PhotographyDetailActivity.class);
				intent.putExtra(MainActivity.STORE_PICTURE, pictureName);
				intent.putExtra(MainActivity.STORE_COMMENTS, comments);
				startActivity(intent);
			} else if (v.getId() == ((Button) findViewById(R.id.btnCall))
					.getId()) {
				/* Call to store's number */
				try {
					Intent callIntent = new Intent(Intent.ACTION_DIAL);
					TextView textViewTelephone = (TextView) findViewById(R.id.txtViewTelephone);
					callIntent.setData(Uri.parse("tel:"
							+ (String) textViewTelephone.getText().toString()));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Log.e("Calling a Phone Number", "Call failed",
							activityException);
				}
			}

		}
	}
}
