package edu.sysuedaily.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.viewpagerindicator.TitlePageIndicator;

import edu.sysuedaily.R;
import edu.sysuedaily.adapter.AttitudePagerAdapter;

public class AttitudePagerFragment extends SherlockFragment {

	static String SELECTED_PAGE = "selected_page";

	int page;

	public static AttitudePagerFragment newInstance(int page) {
		AttitudePagerFragment fragment = new AttitudePagerFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTED_PAGE, page);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		page = getArguments().getInt(SELECTED_PAGE);

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.pagerlayout_activity_attitude, container, false);
		
		TitlePageIndicator indicator = (TitlePageIndicator) layout.findViewById(R.id.indicator);
		ViewPager pager = (ViewPager) layout.findViewById(R.id.pager);
		
		pager.setAdapter(new AttitudePagerAdapter(getFragmentManager()));
		indicator.setViewPager(pager);

		return layout;
	}

}
