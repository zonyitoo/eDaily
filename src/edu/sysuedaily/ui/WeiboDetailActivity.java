package edu.sysuedaily.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.WeiboUtils;

public class WeiboDetailActivity extends SherlockActivity {

	private static final String SHOW_WEIBO_DATA = "show_weibo_data";
	
	TextView contenTextView;
	
	Handler showWeiboHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			try {
				JSONObject obj = new JSONObject(msg.getData().getString(SHOW_WEIBO_DATA));
				contenTextView.setText(obj.getString("text"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibodetail);
		
		contenTextView = (TextView) findViewById(R.id.content);
		
		Intent intent = getIntent();
		String weiboid = intent.getExtras().getString("id");
		String text = intent.getExtras().getString("text");
		contenTextView.setText(text);
		
		new ShowWeiboThread(weiboid).start();
		
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
	
	class ShowWeiboThread extends Thread {
		
		String wid;
		public ShowWeiboThread(String weiboid) {
			wid = weiboid;
		}
		
		@Override
		public void run() {
			JSONObject obj = WeiboUtils.showWeibo(WeiboDetailActivity.this, wid);
			
		}
		
	}
}
