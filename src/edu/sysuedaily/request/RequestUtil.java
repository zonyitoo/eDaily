package edu.sysuedaily.request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

public class RequestUtil {
	private static int callTime = 0;
	
	public static String bitmapToBase64String(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
		byte[] bitmapArray = baos.toByteArray();
		
		String encodeString = Base64.encodeToString(bitmapArray, Base64.DEFAULT);
		
		return encodeString;
	}
	
	/**
	 * 将网址中的内容转换成Bitmap
	 * @param siteUrl
	 * @return
	 * @throws Exception 
	 */
	public static Bitmap urlToBitmap(String siteUrl, int requireSize) {
			//Log.d(LOG_TAG, "call the urlToBitmap " + ++callTime); // called 8 times..
			
			if (siteUrl == null) {
				//Log.d(LOG_TAG, "the url is null, throw Exception");
				return null;
			}
			
			URL url;
			try {
				url = new URL(siteUrl);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(url.openStream(), null, options);
			
				
				// determinte the scale size
				int scale = 1;
				//Log.d(Constant.LOG_TAG, "the outHeight is " + options.outHeight + ", the outWidth is " + options.outWidth);
				 
				scale = computeSampleSize(options, -1, requireSize * requireSize); 
				options.inJustDecodeBounds = false;
				//Log.d(Constant.LOG_TAG, "the scale is " + scale);
				options.inSampleSize = scale;
				Bitmap bitmap = BitmapFactory.decodeStream(url.openStream(), null, options);
				//Log.d(LOG_TAG, "get the bitmap in urltobitmap " + callTime);
				return bitmap;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.d(LOG_TAG, "return null in urltobitmap " + callTime);
			return null;
			
			
	}
	
	public static int computeSampleSize(BitmapFactory.Options options,  
	        int minSideLength, int maxNumOfPixels) {  
	    int initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);  
	  
	    int roundedSize;  
	    if (initialSize <= 8 ) {  
	        roundedSize = 1;  
	        while (roundedSize < initialSize) {  
	            roundedSize <<= 1;  
	        }  
	    } else {  
	        roundedSize = (initialSize + 7) / 8 * 8;  
	    }  
	  
	    return roundedSize;  
	}  
	  
	private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {  
	    double w = options.outWidth;  
	    double h = options.outHeight;  
	  
	    int lowerBound = (maxNumOfPixels == -1) ? 1 :  
	            (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));  
	    int upperBound = (minSideLength == -1) ? 128 :  
	            (int) Math.min(Math.floor(w / minSideLength),  
	            Math.floor(h / minSideLength));  
	  
	    if (upperBound < lowerBound) {  
	        // return the larger one when there is no overlapping zone.  
	        return lowerBound;  
	    }  
	  
	    if ((maxNumOfPixels == -1) &&  
	            (minSideLength == -1)) {  
	        return 1;  
	    } else if (minSideLength == -1) {  
	        return lowerBound;  
	    } else {  
	        return upperBound;  
	    }  
	}
	
	
}
