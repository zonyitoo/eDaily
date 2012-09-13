package edu.sysuedaily.adapter;

import edu.sysuedaily.ui.AttitudeDiscussFragment;
import edu.sysuedaily.ui.AttitudeOpinionFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AttitudePagerAdapter extends FragmentStatePagerAdapter {

	Fragment[] fragments = {null, null, null, null};
	final String[] title = {"投票", "微议", "论见", "专栏"};
	
	public AttitudePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			if (fragments[arg0] == null)
				fragments[arg0] = AttitudeOpinionFragment.newInstance(arg0);
			break;
		case 1:
			if (fragments[arg0] == null)
				fragments[arg0] = AttitudeDiscussFragment.newInstance();
			break;
		case 2:
			if (fragments[arg0] == null)
				fragments[arg0] = AttitudeOpinionFragment.newInstance(arg0);
			break;
		case 3:
			if (fragments[arg0] == null)
				fragments[arg0] = AttitudeOpinionFragment.newInstance(arg0);
			break;
		default:
			break;
		}
		
		if (fragments[arg0] == null)
			fragments[arg0] = AttitudeOpinionFragment.newInstance(arg0);
		
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
