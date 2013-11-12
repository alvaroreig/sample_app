package com.galileo.cursoandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class CountryInfoFragment extends FragmentActivity {
	private WebView webView;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		String country = ((CountryDetailActivity)getActivity()).getCountry();
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_country_info, container,false);
		webView = (WebView) view.findViewById(R.id.webView);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
