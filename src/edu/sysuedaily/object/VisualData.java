package edu.sysuedaily.object;

import android.os.Parcel;
import android.os.Parcelable;

public class VisualData implements Parcelable{
	public String visualId = null;
	public String imageURL = null;
	public String title = null;
	public String text = null;
	
	 public static final Parcelable.Creator<VisualData> CREATOR = new Creator<VisualData>() {  
		  
	        @Override  
	        public VisualData createFromParcel(Parcel source) {  
	        	VisualData visualData = new VisualData();  
	        	visualData.visualId = source.readString();
	        	visualData.imageURL = source.readString();
	        	visualData.title = source.readString();
	        	visualData.text = source.readString();
	            return visualData;  
	        }  
	  
	        @Override  
	        public VisualData[] newArray(int size) {  
	            return new VisualData[size];  
	        }  
	    };  
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(visualId);
		dest.writeString(imageURL);
		dest.writeString(title);
		dest.writeString(text);
	}
}
