package com.galileo.cursoandroid.fragments;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.activities.MainActivity;
import com.galileo.cursoandroid.activities.PhotographyDetailActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StoreDetailsFragment extends Fragment {
	private String storeName;
	private String storeAddress;
	private String storeTelephone;
	private String storeTimeOpen;
	private String storeEmail;
	private String storeWebsite;
	private String storePicture;
	private String storeComments;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		/* Get intent and recover the store values */
		Intent intent = getActivity().getIntent();
		storeName = intent.getStringExtra(MainActivity.STORE_NAME);
		storeAddress = intent.getStringExtra(MainActivity.STORE_ADDRESS);
		storeTelephone = intent.getStringExtra(MainActivity.STORE_TELEPHONE);
		storeTimeOpen = intent.getStringExtra(MainActivity.STORE_TIME_OPEN);
		storeEmail = intent.getStringExtra(MainActivity.STORE_EMAIL);
		storeWebsite = intent.getStringExtra(MainActivity.STORE_WEBSITE);
		storePicture = intent.getStringExtra(MainActivity.STORE_PICTURE);
		storeComments = intent.getStringExtra(MainActivity.STORE_COMMENTS);

		/* Populate the elements with the recovered values */
		TextView textView = (TextView) getView().findViewById(R.id.txtViewName);
		textView.setText(storeName);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) getView().findViewById(R.id.txtViewAddress);
		textView.setText(storeAddress);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) getView().findViewById(R.id.txtViewTelephone);
		textView.setText(storeTelephone);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) getView().findViewById(R.id.txtViewEmail);
		textView.setText(storeEmail);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) getView().findViewById(R.id.txtViewWebsite);
		textView.setText(storeWebsite);
		Linkify.addLinks(textView, Linkify.ALL);

		textView = (TextView) getView().findViewById(R.id.txtViewTImesOpenData);
		textView.setText(storeTimeOpen);
		Linkify.addLinks(textView, Linkify.ALL);

		/* Set up picture button */
		Button pictureButton = (Button) getView().findViewById(R.id.btnPicture);
		PictureOrCallListener listener = new PictureOrCallListener(
				storePicture, storeComments);
		pictureButton.setOnClickListener(listener);

		/* Set up call button */
		Button callButton = (Button) getView().findViewById(R.id.btnCall);
		callButton.setOnClickListener(listener);
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_store_details,
				container, false);
		return view;
	}
	
	private class PictureOrCallListener implements OnClickListener {
		String pictureName;
		String comments;

		public PictureOrCallListener(String pictureName, String comments) {
			super();
			this.pictureName = pictureName;
			this.comments = comments;
		}

		public void onClick(View v) {
			if (v.getId() == ((Button) getView().findViewById(R.id.btnPicture)).getId()) {
				/*
				 * Add picture name and comments to the intent and open
				 * PhotographyDetailActivity
				 */
				Intent intent = new Intent(getActivity(),
						PhotographyDetailActivity.class);
				intent.putExtra(MainActivity.STORE_PICTURE, pictureName);
				intent.putExtra(MainActivity.STORE_COMMENTS, comments);
				startActivity(intent);
			} else if (v.getId() == ((Button) getView().findViewById(R.id.btnCall))
					.getId()) {
				/* Call to store's number */
				try {
					Intent callIntent = new Intent(Intent.ACTION_DIAL);
					TextView textViewTelephone = (TextView) getView().findViewById(R.id.txtViewTelephone);
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
