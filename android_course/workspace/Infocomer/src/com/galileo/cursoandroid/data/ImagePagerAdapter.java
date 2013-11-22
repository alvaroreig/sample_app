package com.galileo.cursoandroid.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.fragments.ImageFragment;

public class ImagePagerAdapter extends FragmentPagerAdapter {

	public ImagePagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		int[] storesImages = new int[]{
				R.drawable.la_musa_de_espronceda,
				R.drawable.la_piola
		};
		Fragment fragment = new ImageFragment();
		Bundle args = new Bundle();
		
		args.putInt(ImageFragment.RESOURCE, storesImages[position]);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
