package edu.sysuedaily.adapter;

import edu.sysuedaily.ui.NewsListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsPagerAdapter extends FragmentPagerAdapter {
	final String[] title = {"头条新闻", "讲座信息", "新闻3", "新闻4", "新闻5"};
	NewsListFragment[] fragments = {null, null, null, null, null};
	
	public NewsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		if (fragments[arg0] == null)
			fragments[arg0] = NewsListFragment.newInstant(arg0);
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
