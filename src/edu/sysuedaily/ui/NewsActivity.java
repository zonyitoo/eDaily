package edu.sysuedaily.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TitlePageIndicator;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.NewsPagerAdapter;

public class NewsActivity extends SherlockFragmentActivity {
	
	ActionBar actionBar;
	
	TitlePageIndicator titlePageIndicator;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		titlePageIndicator = (TitlePageIndicator) findViewById(R.id.titlepageindicator_activity_news);
		viewPager = (ViewPager) findViewById(R.id.viewpager_activity_news);
		
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		viewPager.setAdapter(new NewsPagerAdapter(getSupportFragmentManager()));
		titlePageIndicator.setViewPager(viewPager);
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
