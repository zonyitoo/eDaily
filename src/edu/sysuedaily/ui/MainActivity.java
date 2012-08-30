package edu.sysuedaily.ui;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;

public class MainActivity extends SherlockActivity implements OnClickListener {
	
	ViewPager headlinePager;
	ImageView attitudeImageView;
	ImageView weiboImageView;
	ImageView visualImageView;
	ImageView spitslotImageView;
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
    	//setTheme(com.actionbarsherlock.R.style.Theme_Sherlock_Light_DarkActionBar);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        headlinePager = (ViewPager) findViewById(R.id.pager);
        
        attitudeImageView = (ImageView) findViewById(R.id.attitude_img);
        attitudeImageView.setOnClickListener(this);
        weiboImageView = (ImageView) findViewById(R.id.weibo_img);
        weiboImageView.setOnClickListener(this);
        visualImageView = (ImageView) findViewById(R.id.visual_img);
        visualImageView.setOnClickListener(this);
        spitslotImageView = (ImageView) findViewById(R.id.spitslot_img);
        spitslotImageView.setOnClickListener(this);
        
        newsLayout = (RelativeLayout) findViewById(R.id.head_news);
        
        headDots = new ArrayList<View>();
        headDots.add(findViewById(R.id.dot0));
        headDots.add(findViewById(R.id.dot1));
        headDots.add(findViewById(R.id.dot2));
        headDots.add(findViewById(R.id.dot3));
        headDots.add(findViewById(R.id.dot4));
        
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
        	.setIcon(R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        
        menu.add("MENU")
        	.setIcon(R.drawable.ic_menu_moreoverflow_normal_holo_dark)
        	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println(item.getTitle());
		if (item.getTitle().equals("SEND"))
			;
		else if (item.getTitle().equals("MENU"))
			startActivity(new Intent(this, PreferencesActivity.class));
		
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
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, NewsActivity.class);
					intent.putExtra("selectedNews", v.getId());
					startActivity(intent);
					
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

    
    // Click the 4 parts
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.attitude_img:
			startActivity(new Intent(this, AttitudeActivity.class));
			break;
		case R.id.weibo_img:
			startActivity(new Intent(this, WeiboActivity.class));
			break;
		case R.id.visual_img:
			startActivity(new Intent(this, VisualActivity.class));
			break;
		case R.id.spitslot_img:
			startActivity(new Intent(this, SpitslotActivity.class));

		default:
			break;
		}
		
	}
}
