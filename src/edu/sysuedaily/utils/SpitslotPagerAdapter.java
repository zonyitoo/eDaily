package edu.sysuedaily.utils;

import edu.sysuedaily.ui.SpitslotListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SpitslotPagerAdapter extends FragmentPagerAdapter {
	private SpitslotListFragment[] fragments = {null, null, null};
	private String[] titles = {
			"精彩跟帖",
			"今日排行",
			"本周排行"
	};
	
	public SpitslotPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		if (fragments[arg0] == null)
			fragments[arg0] = SpitslotListFragment.getInstance(arg0);
		
		return fragments[arg0];
	}

	@Override
	public int getCount() {
		
		return fragments.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		
		return titles[position];
	}

}
