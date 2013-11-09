package com.galileo.cursoandroid;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class StoreActivity extends Activity {
	public static String TAG_STORE_ACTIVITY = StoreActivity.class.toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*Set custom title bar and layout*/
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_store);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		
		/* Get intent and recover the store values */
		Intent intent = getIntent();
		String storeName = intent.getStringExtra(MainActivity.STORE_NAME);
		String storeAddress = intent.getStringExtra(MainActivity.STORE_ADDRESS);
		String storeTelephone = intent.getStringExtra(MainActivity.STORE_TELEPHONE);
		String storeTimeOpen = intent.getStringExtra(MainActivity.STORE_TIME_OPEN);
		String storeEmail = intent.getStringExtra(MainActivity.STORE_EMAIL);
		String storeWebsite = intent.getStringExtra(MainActivity.STORE_WEBSITE);
		String storePicture = intent.getStringExtra(MainActivity.STORE_PICTURE);
		String storeComments = intent.getStringExtra(MainActivity.STORE_COMMENTS);

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
		PictureOrCallListener listener = new PictureOrCallListener(storePicture, storeComments);
		pictureButton.setOnClickListener(listener);

		/* Set up call button */
		Button callButton = (Button) findViewById(R.id.btnCall);
		callButton.setOnClickListener(listener);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.store, menu);
		return true;
	}
	
	/*Listener for both buttons in this activity: Call to store and 
	 * access to the Photography activity*/
	private class PictureOrCallListener implements OnClickListener{
		String pictureName;
		String comments;
		public PictureOrCallListener(String pictureName,String comments) {
			super();
			this.pictureName = pictureName;
			this.comments = comments;
		}

		public void onClick(View v) {
			try {
				if (v.getId() ==  ((Button)findViewById(R.id.btnPicture)).getId()){
					/*Add picture name and comments to the intent and open 
					 * PhotographyDetailActivity*/
					Intent intent = new Intent(getApplicationContext(),PhotographyDetailActivity.class);
					intent.putExtra(MainActivity.STORE_PICTURE, pictureName);
					intent.putExtra(MainActivity.STORE_COMMENTS, comments);
					startActivity(intent);	
				}else if (v.getId() ==  ((Button)findViewById(R.id.btnCall)).getId()){
					/*Call to store's number*/
					try {
						Intent callIntent = new Intent(Intent.ACTION_DIAL);
						TextView textViewTelephone = (TextView) findViewById(R.id.txtViewTelephone);
						callIntent.setData(Uri.parse("tel:"	+ (String) textViewTelephone.getText().toString()));
						startActivity(callIntent);
					} catch (ActivityNotFoundException activityException) {
						Log.e("Calling a Phone Number", "Call failed",
								activityException);
					}
				}
				
			} catch (ActivityNotFoundException activityException) {
				Log.e("Calling a Phone Number", "Call failed",
						activityException);
			}
			
		}
		
	}

}
