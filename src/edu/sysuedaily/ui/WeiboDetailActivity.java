package edu.sysuedaily.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import edu.sysuedaily.utils.Utility;
import edu.sysuedaily.utils.WeiboUtils;

public class WeiboDetailActivity extends SherlockActivity {

	private static final String SHOW_WEIBO_DATA = "show_weibo_data";
	
	TextView contenTextView;
	TextView repostTextView;
	TextView timeTextView;
	TextView repostNumberTextView;
	TextView commentNumberTextView;
	
	Handler showWeiboHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			try {
				JSONObject obj = new JSONObject(msg.getData().getString(SHOW_WEIBO_DATA));
				contenTextView.setText(obj.getString("text"));
				Date createTime = CREATE_DATE_FORMATER.parse(obj.getString("created_at"));
				timeTextView.setText(createTime.toLocaleString());
				repostNumberTextView.setText(obj.getString("reposts_count"));
				commentNumberTextView.setText(obj.getString("comments_count"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	
	static final SimpleDateFormat CREATE_DATE_FORMATER = new SimpleDateFormat(
			"EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibodetail);
		
		contenTextView = (TextView) findViewById(R.id.content);
		repostTextView = (TextView) findViewById(R.id.repost);
		timeTextView = (TextView) findViewById(R.id.time);
		repostNumberTextView = (TextView) findViewById(R.id.repost_number);
		commentNumberTextView = (TextView) findViewById(R.id.comment_number);
		
		Intent intent = getIntent();
		String weiboid = intent.getExtras().getString("id");
		String text = intent.getExtras().getString("text");
		contenTextView.setText(Utility.markWeiboAtAndTrend(text));
		
		//new ShowWeiboThread(weiboid).start();
		
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
			try {
				JSONObject obj = WeiboUtils.status_show(WeiboDetailActivity.this, wid);
				Bundle bundle = new Bundle();
				bundle.putString(SHOW_WEIBO_DATA, obj.toString());
				Message msg = new Message();
				msg.setData(bundle);
				showWeiboHandler.sendMessage(msg);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		
	}
}
