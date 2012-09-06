package edu.sysuedaily.adapter;

import java.util.ArrayList;
import java.util.Map;

import edu.sysuedaily.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class VisualPagerAdapter extends PagerAdapter {

	ArrayList<Map<String, Object>> arrayList;
	Context context;
	
	public VisualPagerAdapter(Context context, ArrayList<Map<String, Object>> array) {
		arrayList = array;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.pagercontent_activity_visual, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);
		TextView textView = (TextView) view.findViewById(R.id.text);
		
		((ViewPager) container).addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}
	
	

}
