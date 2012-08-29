package edu.sysuedaily.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

public class WeiboListFragment extends SherlockListFragment {

	public static final String SELECTED_PAGE = "selected_page";
	
	public static WeiboListFragment newInstant(int page) {
		WeiboListFragment fragment = new WeiboListFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTED_PAGE, page);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

}
