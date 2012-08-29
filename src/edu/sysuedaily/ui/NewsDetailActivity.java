package edu.sysuedaily.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import edu.sysuedaily.R;

public class NewsDetailActivity extends SherlockActivity {

	TextView titleTextView;
	TextView fromTextView;
	TextView contentTextView;
	ImageView picImageView;
	LinearLayout wholeContentLinearLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		
		titleTextView = (TextView) findViewById(R.id.title);
		fromTextView = (TextView) findViewById(R.id.from);
		contentTextView = (TextView) findViewById(R.id.content);
		picImageView = (ImageView) findViewById(R.id.image);
		wholeContentLinearLayout = (LinearLayout) findViewById(R.id.wholecontent);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(
				getResources().getDrawable(R.drawable.ab_bg_black));
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		wholeContentLinearLayout.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				startActionMode(new DetailActionMode());
				return true;
			}
		});
		
		Intent args = getIntent();
		contentTextView.setText("selected listnumber: " + args.getIntExtra("selectednews", 0));
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
	
	
	class DetailActionMode implements ActionMode.Callback {

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add("Save")
            .setIcon(R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

			menu.add("Search")
            .setIcon( R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

			menu.add("Refresh")
            .setIcon(R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
			menu.add("Refresh")
            .setIcon(R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			
			mode.finish();
			return true;
		}
		
	}
}
