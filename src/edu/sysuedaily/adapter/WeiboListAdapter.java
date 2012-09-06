package edu.sysuedaily.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.sysuedaily.R;
import edu.sysuedaily.utils.Utility;

public class WeiboListAdapter extends BaseAdapter {

	Context context;
	ArrayList<Map<String, Object>> data;

	public WeiboListAdapter(Context context, ArrayList<Map<String, Object>> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {

		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listcontent_activity_weibo, parent, false);

		TextView text = (TextView) convertView.findViewById(R.id.text);
		TextView weiboname = (TextView) convertView
				.findViewById(R.id.weibo_name);

		Map<String, Object> dataMap = data.get(position);

		String textString = (String) dataMap.get("text");

		text.setText(Utility.markWeiboAtAndTrend(textString));
		weiboname.setText((String) dataMap.get("screen_name"));

		LinearLayout retweet = (LinearLayout) convertView
				.findViewById(R.id.retweet);
		if (dataMap.containsKey("retweet_text")) {
			TextView retweetText = (TextView) convertView
					.findViewById(R.id.retweet_text);
			String retweetTextString = "@"
					+ (String) dataMap.get("retweet_screen_name") + ": "
					+ (String) dataMap.get("retweet_text");
			retweetText.setText(Utility.markWeiboAtAndTrend(retweetTextString));

			retweet.setVisibility(LinearLayout.VISIBLE);
		} else {
			retweet.setVisibility(LinearLayout.GONE);
		}

		return convertView;
	}
}
