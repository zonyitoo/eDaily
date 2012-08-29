package edu.sysuedaily.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TitlePageIndicator;
import com.weibo.net.AccessToken;
import com.weibo.net.DialogError;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.WeiboPagerAdapter;
import edu.sysuedaily.utils.WeiboUtils;

public class WeiboActivity extends SherlockFragmentActivity {

	TitlePageIndicator indicator;
	ViewPager pager;
	
	SharedPreferences prefs;
	
	String token, expiresin;
	
	Thread getUserNameThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo);
		
		indicator = (TitlePageIndicator) findViewById(R.id.indicator);
		pager = (ViewPager) findViewById(R.id.pager);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Weibo.getInstance().setupConsumerConfig(WeiboUtils.CONSUMER_KEY, WeiboUtils.CONSUMER_SECRET);
		token = prefs.getString(WeiboUtils.ACCESS_TOKEN, null);
		expiresin = prefs.getString(WeiboUtils.EXPIRES_IN, null);
		
		getUserNameThread = new GetUserDetailThread();
		
		if (token == null || expiresin == null) {
			WeiboUtils.authorize(this, new WeiboDialogListener() {
				
				@Override
				public void onWeiboException(WeiboException e) {
					Toast.makeText(WeiboActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					WeiboActivity.this.finish();
				}
				
				@Override
				public void onError(DialogError e) {
					Toast.makeText(WeiboActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					WeiboActivity.this.finish();
				}
				
				@Override
				public void onComplete(Bundle values) {
					token = values.getString("access_token");
					expiresin = values.getString("expires_in");
					prefs.edit()
						.putString(WeiboUtils.ACCESS_TOKEN, token)
						.putString(WeiboUtils.EXPIRES_IN, expiresin)
						.commit();
					AccessToken accessToken = new AccessToken(token, WeiboUtils.CONSUMER_SECRET);
					accessToken.setExpiresIn(expiresin);
					Weibo.getInstance().setAccessToken(accessToken);
					getUserNameThread.start();
					pager.setAdapter(new WeiboPagerAdapter(getSupportFragmentManager()));
					indicator.setViewPager(pager);
				}
				
				@Override
				public void onCancel() {
					WeiboActivity.this.finish();
				}
			});
		}
		else {
			AccessToken accessToken = new AccessToken(token, WeiboUtils.CONSUMER_SECRET);
			accessToken.setExpiresIn(expiresin);
			Weibo.getInstance().setAccessToken(accessToken);
			getUserNameThread.start();
			pager.setAdapter(new WeiboPagerAdapter(getSupportFragmentManager()));
			indicator.setViewPager(pager);
		}
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

	class GetUserDetailThread extends Thread {
		
		@Override
		public void run() {
			JSONObject userdetail = WeiboUtils.getUserDetail(WeiboActivity.this);
			String username = prefs.getString(WeiboUtils.USER_SCREEN_NAME, "WRONGNAME");
			try {
				username = userdetail.getString("screen_name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			prefs.edit().putString(WeiboUtils.USER_SCREEN_NAME, username).commit();
		}
		
	}
}
