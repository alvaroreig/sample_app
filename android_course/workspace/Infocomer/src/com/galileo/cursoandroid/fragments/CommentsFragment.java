package com.galileo.cursoandroid.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.galileo.cursoandroid.R;

public class CommentsFragment extends ListFragment {
	private List<HashMap<String, String>> comments = new ArrayList<HashMap<String, String>>();
	private final static String COMMENT = "coment";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_comments,
				container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		/* Link the ViewList with the comments ArrayList */
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), comments,
				android.R.layout.simple_list_item_1,
				new String[] { COMMENT }, new int[] { android.R.id.text1 });
		setListAdapter(adapter);		
		
		/* Load data */
		populateComments();
		adapter.notifyDataSetChanged();
		
		
		super.onActivityCreated(savedInstanceState);
	}

	private void populateComments() {
		HashMap<String, String> comment1 = new HashMap<String, String>();;
		HashMap<String, String> comment2 = new HashMap<String, String>();
		HashMap<String, String> comment3 = new HashMap<String, String>();
		HashMap<String, String> comment4 = new HashMap<String, String>();
		
		/*Should load stored comments*/
		comment1.put(COMMENT, "I like it");
		comment2.put(COMMENT, "I don't like it");
		comment3.put(COMMENT, "I don't like it");
		comment4.put(COMMENT, "I don't like it");
		
		comments.add(comment1);
		comments.add(comment2);
		comments.add(comment3);
		comments.add(comment4);
		
		
		
	}

}
