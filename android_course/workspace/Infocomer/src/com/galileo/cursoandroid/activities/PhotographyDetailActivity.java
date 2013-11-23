package com.galileo.cursoandroid.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import com.galileo.cursoandroid.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotographyDetailActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photography_detail);

		/* Load data from the intent */
		String picture_name = getIntent().getStringExtra(
				MainActivity.STORE_PICTURE);

		/* Populate data into the elements */
		ImageView image = (ImageView) findViewById(R.id.imgViewMain);
		image.setImageResource(getDrawableResourceByName(picture_name));

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
			ImageView imageView = (ImageView) findViewById(R.id.imgViewMain);
			BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
			String path = SaveImage(drawable.getBitmap());
			intent.setType("image/jpeg");
			intent.putExtra(Intent.EXTRA_STREAM,Uri.parse(path));
			startActivity(Intent.createChooser(intent,getResources().getText(R.string.action_share)));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* Method to recover drawable resources using a String instead of an int */
	private int getDrawableResourceByName(String aString) {
		String packageName = getPackageName();
		int resId = getResources().getIdentifier(aString, "drawable",
				packageName);
		return resId;
	}
	
	/*Method to save a bitmap to the sd card*/
	private String SaveImage(Bitmap finalBitmap) {

	    String root = Environment.getExternalStorageDirectory().toString();
	    File myDir = new File(root + "/saved_images");
	    boolean created = false;
	    if (!myDir.exists()){
	    	created=myDir.mkdir();
	    }
	    if (created){
	    	Toast.makeText(this, "Directorio creado", Toast.LENGTH_SHORT).show();
	    }
	    Random generator = new Random();
	    int n = 10000;
	    n = generator.nextInt(n);
	    String fname = "Image-"+ n +".jpg";
	    File file = new File (myDir, fname);
	    if (file.exists ()) file.delete (); 
	    try {
	           FileOutputStream out = new FileOutputStream(file);
	           finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
	           out.flush();
	           out.close();
	           return myDir+"/"+fname;

	    } catch (Exception e) {
	           Log.e("PHOTO",e.toString());
	           return "";
	    }
	}

}
