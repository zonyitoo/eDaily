package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.sysuedaily.R;

public class AttitudeListFragment extends SherlockListFragment {
	
	public static final String SELECTED_PAGE = "selected_page";
	
	public static AttitudeListFragment newInstance(int page) {
		AttitudeListFragment fragment = new AttitudeListFragment();
		Bundle args = new Bundle();
		args.putInt(SELECTED_PAGE, page);
		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		int pos = getArguments().getInt(SELECTED_PAGE);
		
		ArrayList<Map<String, ?>> arrayList2 = new ArrayList<Map<String,?>>();
		
		if (pos == 0) {
			for (int i = 0; i < 10; ++ i) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("TITLE", "新闻条目1");
				arrayList2.add(item);
			}
		}
		else if (pos == 1) {
			for (int i = 0; i < 10; ++ i) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("TITLE", "新闻条目2");
				arrayList2.add(item);
			}
		}
		else if (pos == 2) {
			for (int i = 0; i < 10; ++ i) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("TITLE", "新闻条目3");
				arrayList2.add(item);
			}
		}
		else if (pos == 3) {
			for (int i = 0; i < 10; ++ i) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("TITLE", "新闻条目4");
				arrayList2.add(item);
			}
		}
		else if (pos == 4) {
			for (int i = 0; i < 10; ++ i) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("TITLE", "新闻条目5");
				arrayList2.add(item);
			}
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), arrayList2, 
				R.layout.listcontent_activity_attitude, new String[] {"TITLE"}, new int[] {R.id.title});
		
		setListAdapter(null);
		
		getListView()
			.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.news_listheader, null));
		
		setListAdapter(adapter);
		
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
	}
	
	
}
