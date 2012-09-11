package edu.sysuedaily.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TitlePageIndicator;

import edu.sysuedaily.R;
import edu.sysuedaily.adapter.AttitudePagerAdapter;

public class AttitudeActivity extends SherlockFragmentActivity {

	static Fragment[] fragments = { null, null, null, null };
	
	TitlePageIndicator indicator;
	ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pagerlayout_activity_attitude);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		indicator = (TitlePageIndicator) findViewById(R.id.indicator);
		pager = (ViewPager) findViewById(R.id.pager);
		
		pager.setAdapter(new AttitudePagerAdapter(getSupportFragmentManager()));
		indicator.setViewPager(pager);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}

		return true;
	}

}
