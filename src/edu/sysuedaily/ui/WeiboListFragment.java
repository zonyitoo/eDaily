package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.Utility;
import edu.sysuedaily.utils.WeiboListAdapter;
import edu.sysuedaily.utils.WeiboUtils;

public class WeiboListFragment extends SherlockListFragment {

	public static final String SELECTED_PAGE = "selected_page";
	public static final String EDAILY_UID = "1899452321";

	static final String TIMELINE_DATA = "timeline_data";

	static int edailypage = 1;

	Handler timelineDataHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			try {
				JSONObject timeline = new JSONObject(msg.getData().getString(
						TIMELINE_DATA));
				JSONArray statuses = timeline.getJSONArray("statuses");

				for (int i = 0; i < statuses.length(); ++i) {
					JSONObject obj = statuses.getJSONObject(i);
					JSONObject user = obj.getJSONObject("user");
					HashMap<String, Object> hashmap = new HashMap<String, Object>();
					hashmap.put("text", obj.getString("text"));
					hashmap.put("id", obj.getString("id"));
					hashmap.put("userid", user.getString("id"));
					hashmap.put("screen_name", user.getString("screen_name"));
					if (obj.has("retweeted_status")) {
						JSONObject retweet = obj
								.getJSONObject("retweeted_status");
						hashmap.put("retweet_text", retweet.getString("text"));
						hashmap.put("retweet_userid",
								retweet.getJSONObject("user").getString("id"));
						hashmap.put("retweet_id", retweet.getString("id"));
						hashmap.put("retweet_screen_name", retweet
								.getJSONObject("user").getString("screen_name"));
					}

					edailyTimelineArrayList.add(hashmap);
				}

				WeiboListAdapter adapter = new WeiboListAdapter(getActivity(),
						edailyTimelineArrayList);

				setListAdapter(adapter);

				getListView().setSelection(currentListHeadPos);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			super.handleMessage(msg);
		}

	};

	int page;
	ArrayList<Map<String, Object>> edailyTimelineArrayList = new ArrayList<Map<String, Object>>();

	int currentListHeadPos = 0;

	public static WeiboListFragment newInstant(int page) {
		WeiboListFragment fragment = new WeiboListFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTED_PAGE, page);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(getActivity(), WeiboDetailActivity.class);
		intent.putExtra("id", (String) edailyTimelineArrayList.get(position)
				.get("id"));
		intent.putExtra("text", (String) edailyTimelineArrayList.get(position)
				.get("text"));
		startActivity(intent);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		page = getArguments().getInt(SELECTED_PAGE);
		switch (page) {
		case 0:
			showEdailyTimeline();
			break;

		default:
			break;
		}

		RelativeLayout footer = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.listfooter_load, null);
		getListView().addFooterView(footer);
		getListView().setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (view.getLastVisiblePosition() == view.getCount() - 1) {
					switch (page) {
					case 0:
						showEdailyTimeline();
						break;

					default:
						break;
					}
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				currentListHeadPos = view.getFirstVisiblePosition();

			}
		});

		super.onActivityCreated(savedInstanceState);
	}

	private void showEdailyTimeline() {
		new GetTimelineThread(EDAILY_UID, edailypage++).start();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	class GetTimelineThread extends Thread {

		String uid;
		int getpage;

		public GetTimelineThread(String uid, int page) {
			this.uid = uid;
			this.getpage = page;
		}

		@Override
		public void run() {
			JSONObject timeline = WeiboUtils.getUserTimeline(getActivity(),
					uid, getpage);
			if (timeline != null) {
				Message message = new Message();
				Bundle data = new Bundle();
				data.putString(TIMELINE_DATA, timeline.toString());
				message.setData(data);
				timelineDataHandler.sendMessage(message);
			}
		}

	}

}
