package edu.sysuedaily.ui;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;
import edu.sysuedaily.application.EDailyApplication;
import edu.sysuedaily.asyncloader.AsyncImageLoader;
import edu.sysuedaily.object.VisualData;
import edu.sysuedaily.utils.Constant;

public class VisualPictShowActivity extends SherlockFragmentActivity implements OnPageChangeListener {

	ViewPager viewPager = null;
	TextView titleTextView = null;
	TextView pictNumTextView = null;
	
	ArrayList<VisualData> visualDataList = null;
	int curPos = 0;
	
	Bitmap[] bitmapList = null;
	AsyncImageLoader imageLoader = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_pict_show);
        ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		viewPager = (ViewPager) findViewById(R.id.visual_pict_show_view_pager);
		titleTextView = (TextView) findViewById(R.id.visual_pict_show_title_text);
		pictNumTextView = (TextView) findViewById(R.id.visual_pict_show_pict_num_text);
		
		curPos = this.getIntent().getIntExtra(Constant.ExtraKey.VISULA_IMG_POS, 0);
		visualDataList = this.getIntent().getParcelableArrayListExtra(Constant.ExtraKey.VISUAL_DATA_LIST);
		
		titleTextView.setText(visualDataList.get(0).title);
		pictNumTextView.setText("1/" + visualDataList.size());
		
		EDailyApplication application = (EDailyApplication) this.getApplication();
		imageLoader = new AsyncImageLoader(application.getNetworkState(), this);
		bitmapList = new Bitmap[visualDataList.size()];
		
		for (int i = 0; i < bitmapList.length; i++) {
			imageLoader.loadImage(i, visualDataList.get(i).imageURL,
					new AsyncImageLoader.ImageLoadListener() {
						
						@Override
						public void onImageLoad(Integer rowNum, Bitmap bitmap) {
							// TODO Auto-generated method stub
							if (bitmap != null)
								bitmapList[rowNum] = bitmap;
						}
						
						@Override
						public void onError(Integer rowNum) {
							// TODO Auto-generated method stub
							bitmapList[rowNum] = BitmapFactory.decodeResource(getResources(), R.drawable.loading);
						}
					}, 300);
		}
		
		viewPager.setAdapter(new VisualPictAdapter());
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(curPos);
    }
    
    
    @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
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
    
    private class VisualPictAdapter extends PagerAdapter {

    	@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return visualDataList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			View view = null;
			if (bitmapList[position] != null) {
				view = new ImageView(VisualPictShowActivity.this);
	    		((ImageView) view).setImageBitmap(bitmapList[position]);
	    		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	    		((ImageView) view).setScaleType(ScaleType.FIT_XY);
	    		((ViewPager) container).addView(view);
	    		return view;
			}
			else {
				view = new ProgressBar(VisualPictShowActivity.this);
				((ProgressBar)view).setIndeterminate(true);
				//view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				((ViewPager) container).addView(view);
				return view;
			}
		}
    	
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}
    	
		
		
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		VisualData visualData = this.visualDataList.get(position);
		this.titleTextView.setText(visualData.title);
		this.pictNumTextView.setText((position + 1) + "/" + visualDataList.size());
	}

}
