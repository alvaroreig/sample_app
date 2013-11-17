package com.galileo.cursoandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotographyDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*Set up custom title bar*/
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_photography_detail);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		
		/*Load data from the intent*/
		String picture_name = getIntent().getStringExtra(MainActivity.STORE_PICTURE);
		String comments = getIntent().getStringExtra(MainActivity.STORE_COMMENTS);
		
		/*Populate data into the elements*/
		ImageView image = (ImageView) findViewById(R.id.imgViewMain);
		image.setImageResource(getDrawableResourceByName(picture_name));
		
		TextView commentsTextView = (TextView) findViewById(R.id.txtViewComments);
		commentsTextView.setText(comments);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.photography_detail, menu);
		return true;
	}
	
	/* Method to recover drawable resources using a String instead of an int */
	private int getDrawableResourceByName(String aString) {
		String packageName = getPackageName();
		int resId = getResources()
				.getIdentifier(aString, "drawable", packageName);
		return resId;
	}

}
