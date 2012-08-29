package edu.sysuedaily.utils;

import edu.sysuedaily.ui.WeiboListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WeiboPagerAdapter extends FragmentPagerAdapter {

	final String[] titleStrings = {"微博", "微话题", "微人物"};
	WeiboListFragment[] fragments = {null, null, null};
	
	public WeiboPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		if (fragments[arg0] == null)
			fragments[arg0] = WeiboListFragment.newInstant(arg0);
		
		return fragments[arg0];
	}

	@Override
	public int getCount() {
		return fragments.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleStrings[position];
	}

}
