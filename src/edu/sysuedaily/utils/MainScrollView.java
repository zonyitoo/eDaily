package edu.sysuedaily.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MainScrollView extends ScrollView {

	private float xDistance, yDistance, xLast, yLast;
	
	public MainScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO 自动生成的构造函数存根
	}

	public MainScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	public MainScrollView(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		 switch (ev.getAction()) {
         case MotionEvent.ACTION_DOWN:
             xDistance = yDistance = 0f;
             xLast = ev.getX();
             yLast = ev.getY();
             break;
         case MotionEvent.ACTION_MOVE:
             final float curX = ev.getX();
             final float curY = ev.getY();
             
             xDistance += Math.abs(curX - xLast);
             yDistance += Math.abs(curY - yLast);
             xLast = curX;
             yLast = curY;
             
             if(xDistance > yDistance){
                 return false;
             }  
     }
		return super.onInterceptTouchEvent(ev);
	}

}
