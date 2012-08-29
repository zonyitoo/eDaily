package edu.sysuedaily.utils;

import edu.sysuedaily.ui.AttitudeListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AttitudePagerAdapter extends FragmentPagerAdapter {

	AttitudeListFragment[] fragments = {null, null, null, null};
	final String[] title = {"微议", "论见", "投票", "影评"};
	
	public AttitudePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		if (fragments[arg0] == null)
			fragments[arg0] = AttitudeListFragment.newInstant(arg0);
		
		return fragments[arg0];
	}

	@Override
	public int getCount() {
		return fragments.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return title[position];
	}
}
