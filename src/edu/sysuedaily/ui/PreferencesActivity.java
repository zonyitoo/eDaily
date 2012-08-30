package edu.sysuedaily.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;
import com.weibo.net.AccessToken;
import com.weibo.net.Weibo;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.WeiboUtils;

public class PreferencesActivity extends SherlockPreferenceActivity implements OnPreferenceClickListener {

	ActionBar actionBar;
	Preference boundedWeibo;
	SharedPreferences prefs;
	
	String screen_name = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.prefs);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		boundedWeibo = findPreference("prefs_bounded_weibo");
		screen_name = prefs.getString(WeiboUtils.USER_SCREEN_NAME, null);
		if (screen_name != null) {
			boundedWeibo.setTitle(screen_name);
			boundedWeibo.setSummary(R.string.prefs_click_to_unbound_weibo);
		}
		boundedWeibo.setOnPreferenceClickListener(this);
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
	public boolean onPreferenceClick(Preference preference) {
		if (preference.getKey().equals("prefs_bounded_weibo")) {		
			// do unbound
			if (screen_name != null) {
				prefs.edit()
					.remove(WeiboUtils.ACCESS_TOKEN)
					.remove(WeiboUtils.EXPIRES_IN)
					.remove(WeiboUtils.USER_SCREEN_NAME)
					.commit();
				boundedWeibo.setTitle(R.string.prefs_unbound_weibo);
				boundedWeibo.setSummary(R.string.prefs_click_to_bound_weibo);
			}
			// bound
			else {
				WeiboUtils.authorize(this, new Handler() {

					@Override
					public void handleMessage(Message msg) {
						switch (msg.what) {
						case WeiboUtils.AUTHORIZE_CANCEL:
							Toast.makeText(PreferencesActivity.this, (String) msg.getData().get("message"), Toast.LENGTH_SHORT).show();
							break;
						case WeiboUtils.AUTHORIZE_COMPLETE:
							String token = (String) msg.getData().getString(WeiboUtils.ACCESS_TOKEN);
							String expiresin = (String) msg.getData().getString(WeiboUtils.EXPIRES_IN);
							AccessToken accessToken = new AccessToken(token, WeiboUtils.CONSUMER_SECRET);
							accessToken.setExpiresIn(expiresin);
							Weibo.getInstance().setAccessToken(accessToken);
							
							boundedWeibo.setTitle(prefs.getString(WeiboUtils.USER_SCREEN_NAME, ""));
							boundedWeibo.setSummary(R.string.prefs_click_to_unbound_weibo);
							screen_name = null;
							break;
						case WeiboUtils.AUTHORIZE_ERROR:
							Toast.makeText(PreferencesActivity.this, (String) msg.getData().get("message"), Toast.LENGTH_SHORT).show();
							break;
						case WeiboUtils.AUTHORIZE_EXCEPTION:
							Toast.makeText(PreferencesActivity.this, (String) msg.getData().get("message"), Toast.LENGTH_SHORT).show();
							break;

						default:
							break;
						}
					}
					
				});
				
			}
		}
		return false;
	}
	
}
