package edu.sysuedaily.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;
import edu.sysuedaily.utils.WeiboUtils;

public class PreferencesActivity extends SherlockPreferenceActivity {

	ActionBar actionBar;
	Preference boundedWeibo;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.prefs);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		boundedWeibo = findPreference("prefs_bounded_weibo");
		String screen_name = prefs.getString(WeiboUtils.USER_SCREEN_NAME, "未绑定微博");
		boundedWeibo.setTitle(screen_name);
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
