package edu.sysuedaily.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;
import edu.sysuedaily.adapter.VisualAdapter;
import edu.sysuedaily.application.EDailyApplication;
import edu.sysuedaily.object.VisualData;
import edu.sysuedaily.utils.Constant;

public class VisualActivity extends SherlockFragmentActivity implements OnItemClickListener {

	//ViewPager pager;
	//CirclePageIndicator indicator;
	
	private GridView gridView = null;
	private ArrayList<Bitmap> bitmaps = null;
	private ArrayList<VisualData> visualDatas = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visual);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		this.gridView = (GridView) findViewById(R.id.visual_grid_grid);
		
		visualDatas = new ArrayList<VisualData>();
		VisualData dumpData = new VisualData();
		dumpData.imageURL = "http://ww2.sinaimg.cn/bmiddle/a8e3528egw1dwvc4nhlovj.jpg";
		dumpData.text = "this is a text";
		dumpData.title = "this is a title";
		dumpData.visualId = "1";
		for (int i = 0; i < 10; i++) {
			visualDatas.add(dumpData);
		}
		
		EDailyApplication application = (EDailyApplication) this.getApplication();
		VisualAdapter adapter = new VisualAdapter(this, gridView, visualDatas, application.getNetworkState());
		this.gridView.setAdapter(adapter);
		
		this.gridView.setOnItemClickListener(this);
		
		/*
		pager = (ViewPager) findViewById(R.id.pager);
		//indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		
		
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 10; ++ i) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			arrayList.add(map);
		}
		
		
		pager.setAdapter(new VisualPagerAdapter(this, arrayList));
		//indicator.setViewPager(pager);*/
		
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// go to the picture detail activity
		Intent intent = new Intent(this, VisualPictShowActivity.class);
		intent.putExtra(Constant.ExtraKey.VISULA_IMG_POS, position);
		intent.putExtra(Constant.ExtraKey.VISUAL_DATA_LIST, visualDatas);
		startActivity(intent);
	}
}
