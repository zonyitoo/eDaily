package edu.sysuedaily.adapter;

import java.util.ArrayList;

import edu.sysuedaily.R;
import edu.sysuedaily.object.VisualData;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.TextureView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

public class VisualAdapter extends BaseAsyncAdapter<VisualData>{

	public VisualAdapter(Context context, AbsListView view,
			ArrayList<VisualData> dataSet,
			boolean netStatus) {
		super(context, view, dataSet, R.id.visual_grid_image, netStatus, R.layout.visual_grid,
				R.drawable.loading);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void bindView(int position, View convertView) {
		// TODO Auto-generated method stub
		VisualData visualData = this.dataSet.get(position);
		TextView textView = (TextView) convertView.findViewById(R.id.visual_grid_intro_text);
		textView.setText(visualData.title);
	}

	@Override
	protected void setImage(int position, View convertView) {
		// TODO Auto-generated method stub
		// should be wrote in this way
		VisualData visualData = this.dataSet.get(position);
		int requireSize = 100; // I hope it will store in the util class
		this.defaultImageLoader.loadImage(position, visualData.imageURL, this.defaultListener, requireSize);
	}

}
