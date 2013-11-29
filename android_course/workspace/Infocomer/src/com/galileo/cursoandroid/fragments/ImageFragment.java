package com.galileo.cursoandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.galileo.cursoandroid.R;

public class ImageFragment extends Fragment {
	public static final String RESOURCE = "resource";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_image, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.viewPagerSingleImageView);
		
		Bundle args = getArguments();
		imageView.setImageResource(args.getInt(RESOURCE));
		
		
		
		
		return view;
	}

}
