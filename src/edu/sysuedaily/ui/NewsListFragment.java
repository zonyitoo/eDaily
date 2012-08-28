package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.sysuedaily.R;

public class NewsListFragment extends SherlockListFragment {
	
	public static NewsListFragment newInstant(int position) {
		NewsListFragment fragment = new NewsListFragment();
		Bundle argsBundle = new Bundle();
		argsBundle.putInt("showId", position);
		fragment.setArguments(argsBundle);
		
		return fragment;
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		int pos = getArguments().getInt("showId");
		
		System.out.println(pos);
		
		super.onCreate(savedInstanceState);
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		int pos = getArguments().getInt("showId");
		
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
				R.layout.listcontent_activity_news, new String[] {"TITLE"}, new int[] {R.id.textview_newstitle_listcontent_activity_news});
		
		setListAdapter(adapter);
		
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		startActivity(new Intent(getActivity(), NewsDetailActivity.class));
	}

}
