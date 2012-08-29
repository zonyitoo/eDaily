package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.WeiboUtils;

public class WeiboListFragment extends SherlockListFragment {

	public static final String SELECTED_PAGE = "selected_page";
	public static final String EDAILY_UID = "1899452321";
	
	static final String TIMELINE_DATA = "timeline_data";
	
	static final String[] EDAILY_TIMELINE_FROM = {"text"};
	static final int[] TO = {R.id.content};
	
	Handler timelineDataHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			try {
				JSONObject timeline = new JSONObject(msg.getData().getString(TIMELINE_DATA));
				JSONArray statuses = timeline.getJSONArray("statuses");
				
				for (int i = 0; i < statuses.length(); ++ i) {
					JSONObject obj = statuses.getJSONObject(i);
					HashMap<String, Object> hashmap = new HashMap<String, Object>();
					hashmap.put(EDAILY_TIMELINE_FROM[0], obj.get("text"));
					edailyTimelineArrayList.add(hashmap);
				}
				
				SimpleAdapter adapter = new SimpleAdapter(getActivity(), 
						edailyTimelineArrayList, 
						R.layout.listcontent_activity_weibo,
						EDAILY_TIMELINE_FROM,
						TO);
				setListAdapter(adapter);
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
	ArrayList<Map<String, Object>> edailyTimelineArrayList = null;
	
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
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
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

		super.onActivityCreated(savedInstanceState);
	}
	
	private void showEdailyTimeline() {
		edailyTimelineArrayList = new ArrayList<Map<String,Object>>();
		new GetTimelineThread(EDAILY_UID).start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	
	class GetTimelineThread extends Thread {
		
		String uid;
		
		public GetTimelineThread(String uid) {
			this.uid = uid;
		}
		
		@Override
		public void run() {
			JSONObject timeline = WeiboUtils.getUserTimeline(getActivity(), uid);
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
