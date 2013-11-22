package com.galileo.cursoandroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.data.ImagePagerAdapter;

public class StoresImagesFragment extends Fragment {
	ViewPager viewpager;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ImagePagerAdapter adapter = new ImagePagerAdapter(getChildFragmentManager());
		viewpager.setAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stores_images,
				container, false);
		
		viewpager = (ViewPager) view.findViewById(R.id.pager);
		return view;
	}

}
