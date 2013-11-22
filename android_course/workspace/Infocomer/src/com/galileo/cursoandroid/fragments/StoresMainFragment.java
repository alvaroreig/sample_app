package com.galileo.cursoandroid.fragments;

import com.galileo.cursoandroid.R;
import com.galileo.cursoandroid.activities.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StoresMainFragment extends Fragment implements TabListener {
	public final static String STORE_NAME = "name";
	public final static String STORE_ADDRESS = "address";
	public final static String STORE_TELEPHONE = "telephone";
	public final static String STORE_TIME_OPEN = "time_open";
	public final static String STORE_EMAIL = "email";
	public final static String STORE_WEBSITE = "website";
	public final static String STORE_PICTURE = "picture";
	public final static String STORE_COMMENTS = "comments";

	Fragment[] fragments = new Fragment[] { new CountriesListFragment(),
			new MapFragment(), new StoresImagesFragment() };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		FragmentManager manager = getActivity().getSupportFragmentManager();
		manager.beginTransaction().add(R.id.mainContent, fragments[0])
				.add(R.id.mainContent, fragments[1])
				.add(R.id.mainContent, fragments[2]).commit();

		ActionBar actionbar = ((MainActivity) getActivity())
				.getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionbar.addTab(actionbar.newTab()
				.setText(R.string.fragment_title_list).setTabListener(this));
		actionbar.addTab(actionbar.newTab()
				.setText(R.string.fragment_title_map).setTabListener(this));
		actionbar.addTab(actionbar.newTab()
				.setText(R.string.fragment_title_viewpager)
				.setTabListener(this));
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_stores_main_fragment, null);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		setContent(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public void setContent(int tab) {
		Fragment toHide = null;
		Fragment toShow = null;
		Fragment toHideTwo = null;
		switch (tab) {
		case 0:
			toHide = fragments[1];
			toShow = fragments[0];
			toHideTwo = fragments[2];
			break;
		case 1:
			toHide = fragments[0];
			toShow = fragments[1];
			toHideTwo = fragments[2];
			break;
		case 2:
			toHide = fragments[1];
			toShow = fragments[2];
			toHideTwo = fragments[0];
			break;
		}

		FragmentManager manager = getActivity().getSupportFragmentManager();

		manager.beginTransaction().hide(toHide).hide(toHideTwo).show(toShow)
				.commit();
	}

}
