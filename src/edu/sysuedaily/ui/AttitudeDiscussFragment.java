package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockFragment;

import edu.sysuedaily.R;

public class AttitudeDiscussFragment extends SherlockFragment {

	RelativeLayout relativeLayout;
	ListView menuListView;
	ListView listView;
	LinearLayout menulistLayout;
	ImageView handleImageView;
	RelativeLayout contentListLayout;

	boolean isOpened = false;

	public static AttitudeDiscussFragment newInstance() {
		AttitudeDiscussFragment fragment = new AttitudeDiscussFragment();

		return fragment;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		LayoutInflater inflater = LayoutInflater.from(getActivity());
		LinearLayout headerLayout = (LinearLayout) inflater.inflate(
				R.layout.listcontent_header_discuss, null);

		listView.addHeaderView(headerLayout);

		ArrayList<Map<String, ?>> arrayList2 = new ArrayList<Map<String, ?>>();
		for (int i = 0; i < 10; ++i) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("TITLE", "新闻条目1");
			arrayList2.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), arrayList2,
				R.layout.listcontent_opinion_activity_attitude,
				new String[] {}, new int[] {});

		listView.setAdapter(adapter);

		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		relativeLayout = (RelativeLayout) inflater.inflate(
				R.layout.fragment_discuss_attitude, container, false);
		menuListView = (ListView) relativeLayout.findViewById(R.id.menulist);
		listView = (ListView) relativeLayout.findViewById(R.id.list);
		menulistLayout = (LinearLayout) relativeLayout
				.findViewById(R.id.menulist_layout);
		contentListLayout = (RelativeLayout) relativeLayout
				.findViewById(R.id.contentlist_layout);
		handleImageView = (ImageView) relativeLayout.findViewById(R.id.handler);

		handleImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isOpened) {
					isOpened = false;
					closeTopicList();
				} else {
					isOpened = true;
					openTopicList();
				}

			}
		});
		

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				

			}
		});

		return relativeLayout;
	}

	void closeTopicList() {
		int width = menulistLayout.getLayoutParams().width;
		final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentListLayout
				.getLayoutParams();

		params.setMargins(0, 0, 0, 0);

		TranslateAnimation animation = new TranslateAnimation(-width, 0, 0, 0);
		animation.setDuration(150);
		animation.setInterpolator(new LinearInterpolator());

		contentListLayout.setAnimation(animation);
		contentListLayout.startLayoutAnimation();
		contentListLayout.setLayoutParams(params);

		listView.setEnabled(true);
	}

	void openTopicList() {
		int width = menulistLayout.getLayoutParams().width;
		final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentListLayout
				.getLayoutParams();

		params.setMargins(-width, 0, width, 0);

		TranslateAnimation animation = new TranslateAnimation(width, 0, 0, 0);
		animation.setDuration(150);
		animation.setInterpolator(new LinearInterpolator());
		contentListLayout.setAnimation(animation);
		contentListLayout.startLayoutAnimation();
		contentListLayout.setLayoutParams(params);

		listView.setEnabled(false);
	}
}
