package edu.sysuedaily.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import edu.sysuedaily.R;

public class SpecialFragment extends SherlockFragment {

	ListView listView;
	ImageView imageView;
	TextView authorIntro;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_special_attitude, container, false);
		listView = (ListView) contentView.findViewById(R.id.list);
		imageView = (ImageView) contentView.findViewById(R.id.image);
		authorIntro = (TextView) contentView.findViewById(R.id.introduce);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
