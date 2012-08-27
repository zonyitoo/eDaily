package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;

public class MainActivity extends SherlockActivity {
	
	ViewPager headlinePager;
	ImageView attitudeImageView;
	ImageView weiboImageView;
	ImageView perspectiveImageView;
	ImageView rediculeImageView;
	RelativeLayout newsLayout;

	ArrayList<View> headDots;
	int pagerCurrent = 0;
	
	ScheduledExecutorService headAutoScrollSched;
	final Handler headAutoScrollHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (headlinePager != null)
				headlinePager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}
    	
    };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(com.actionbarsherlock.R.style.Theme_Sherlock_Light);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        headlinePager 			= 	(ViewPager) findViewById(R.id.viewpager_news_activity_main);
        
        attitudeImageView 		= 	(ImageView) findViewById(R.id.imageview_attitude_activity_main);
        weiboImageView 			= 	(ImageView) findViewById(R.id.imageview_weibo_activity_main);
        perspectiveImageView 	= 	(ImageView) findViewById(R.id.imageview_perspective_activity_main);
        rediculeImageView 		= 	(ImageView) findViewById(R.id.imageview_redicule_activity_main);
        
        newsLayout				= 	(RelativeLayout) findViewById(R.id.relativelayout_news_activity_main);
        
        headDots = new ArrayList<View>();
        headDots.add(findViewById(R.id.view_dot0_viewpager_activity_main));
        headDots.add(findViewById(R.id.view_dot1_viewpager_activity_main));
        headDots.add(findViewById(R.id.view_dot2_viewpager_activity_main));
        headDots.add(findViewById(R.id.view_dot3_viewpager_activity_main));
        headDots.add(findViewById(R.id.view_dot4_viewpager_activity_main));
        
        int scrwidth = getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams newsLayoutParams = (android.widget.LinearLayout.LayoutParams) newsLayout.getLayoutParams();
        newsLayoutParams.height = scrwidth / 3 * 2;
        newsLayout.setLayoutParams(newsLayoutParams);
        RelativeLayout.LayoutParams headlinePagerLayoutParams = (android.widget.RelativeLayout.LayoutParams) headlinePager.getLayoutParams();
        headlinePagerLayoutParams.height = scrwidth / 3 * 2;
        headlinePager.setLayoutParams(headlinePagerLayoutParams);
        
        headlinePager.setOnPageChangeListener(new OnPageChangeListener() {
			
			public void onPageSelected(int position) {
				headDots.get(pagerCurrent).setBackgroundResource(R.drawable.dot_normal);
				headDots.get(position).setBackgroundResource(R.drawable.dot_focused);
				pagerCurrent = position;
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        headlinePager.setAdapter(new HeadPagerAdapter());
    }
    
    @Override
	protected void onStart() {
		headAutoScrollSched = Executors.newSingleThreadScheduledExecutor();
		headAutoScrollSched.scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				synchronized (headlinePager) {
					headAutoScrollHandler.sendEmptyMessage((pagerCurrent + 1) % 5);
				}
			}
		}, 5, 5, TimeUnit.SECONDS);
		super.onStart();
	}
    
    
    
    @Override
	protected void onStop() {
		headAutoScrollSched.shutdown();
		super.onStop();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Used to put dark icons on light action bar
        //boolean isLight = SampleList.THEME == R.style.Theme_Sherlock_Light;

        menu.add("SEND")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case 0:
			
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
    
    private class HeadPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView imageView = new ImageView(MainActivity.this);
			imageView.setImageResource(R.drawable.loading);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			imageView.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					startActivity(new Intent(MainActivity.this, NewsActivity.class));
					
				}
			});
			((ViewPager) container).addView(imageView);
			return imageView;
		}
    	
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}
    }
}
