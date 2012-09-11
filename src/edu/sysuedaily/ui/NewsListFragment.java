package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.sysuedaily.R;

public class NewsListFragment extends SherlockListFragment {
	
	public static final String SELECTED_PAGE = "selected_page";
	
	public static NewsListFragment newInstant(int position) {
		NewsListFragment fragment = new NewsListFragment();
		Bundle argsBundle = new Bundle();
		argsBundle.putInt(SELECTED_PAGE, position);
		fragment.setArguments(argsBundle);
		
		return fragment;
	}

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//return inflater.inflate(R.layout.fragment_news, container, false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
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
				R.layout.listcontent_activity_news, new String[] {"TITLE"}, new int[] {R.id.title});
		
		setListAdapter(null);
		
		getListView()
			.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.listcontent_header_image_title, null));
		
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
		Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
		intent.putExtra("selectednews", position);
		
		startActivity(intent);
	}

}
