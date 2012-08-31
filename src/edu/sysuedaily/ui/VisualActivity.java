package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.CirclePageIndicator;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.VisualPagerAdapter;

public class VisualActivity extends SherlockFragmentActivity {

	ViewPager pager;
	CirclePageIndicator indicator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visual);
		
		pager = (ViewPager) findViewById(R.id.pager);
		indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 10; ++ i) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			arrayList.add(map);
		}
		
		pager.setAdapter(new VisualPagerAdapter(this, arrayList));
		indicator.setViewPager(pager);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
}
