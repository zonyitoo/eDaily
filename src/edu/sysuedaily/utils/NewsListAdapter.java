package edu.sysuedaily.utils;

import java.util.ArrayList;
import java.util.Map;

import edu.sysuedaily.R;

import android.R.integer;
import android.content.ContentResolver;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {
	
	RelativeLayout headLayout;
	LinearLayout contentLayout;
	ListView parentListView;
	
	Context context;
	
	ArrayList<Map<String, ?>> arrayList = null;
	Cursor cursor = null;
	
	public void init(Context context, ListView parent) {
		this.context = context;
		parentListView = parent;
		LayoutInflater inflater = LayoutInflater.from(context);
		headLayout = (RelativeLayout) inflater.inflate(R.layout.listcontent_header_activity_news, parentListView);
		contentLayout = (LinearLayout) inflater.inflate(R.layout.listcontent_activity_news, parentListView);
	}
	
	public NewsListAdapter(Context context, ListView parent, 
			ArrayList<Map<String, ?>> arrayList) {
		init(context, parent);
		this.arrayList = arrayList;
	}
	
	public NewsListAdapter(Context context, ListView parent, 
			Cursor cursor) {
		init(context, parent);
		this.cursor = cursor;
	}
	
	@Override
	public int getCount() {
		if (arrayList != null)
			return arrayList.size();
		else
			return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		if (arrayList != null)
			return arrayList.get(position);
		else 
			return cursor;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (position == 0) {
			if (arrayList != null) {
				ImageView imageView = (ImageView) headLayout.findViewById(R.id.imageview_listcontent_header_activity_news);
				
			}
			else {
				
			}
		}
		else {
			
		}
		
		return null;
	}

}
