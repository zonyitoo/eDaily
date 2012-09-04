package edu.sysuedaily.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TitlePageIndicator;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.SpitslotPagerAdapter;

public class SpitslotActivity extends SherlockFragmentActivity {

	TitlePageIndicator indicator;
	ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spitslot);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		indicator = (TitlePageIndicator) findViewById(R.id.indicator);
		pager = (ViewPager) findViewById(R.id.pager);
		
		pager.setAdapter(new SpitslotPagerAdapter(getSupportFragmentManager()));
		indicator.setViewPager(pager);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Used to put dark icons on light action bar
        //boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;

        menu.add("SEND")
        	.setIcon(R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {	
		
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		else if (item.getTitle() == "SEND") {
			
		}
		
		
		return super.onOptionsItemSelected(item);
	}

}
