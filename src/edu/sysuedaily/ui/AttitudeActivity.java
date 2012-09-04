package edu.sysuedaily.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import edu.sysuedaily.R;

public class AttitudeActivity extends SherlockFragmentActivity implements
		OnNavigationListener {

	static Fragment[] fragments = { null, null, null, null };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		ArrayAdapter<CharSequence> naviAdapter = ArrayAdapter
				.createFromResource(actionBar.getThemedContext(),
						R.array.attitude_first_classification,
						R.layout.sherlock_spinner_item);
		naviAdapter
				.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
		actionBar.setListNavigationCallbacks(naviAdapter, this);
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

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		
		Fragment fragment = null;
		
		if (fragments[itemPosition] == null) {
			switch (itemPosition) {
			case 0:
			case 1:
				fragment = AttitudeListFragment
						.newInstance(itemPosition);
				break;
			case 2:
			case 3:
				fragment = AttitudePagerFragment
						.newInstance(itemPosition);
				break;
			default:
				break;
			}
		}

		transaction.replace(android.R.id.content, fragment).commit();

		return true;
	}

}
