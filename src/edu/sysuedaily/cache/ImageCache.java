package edu.sysuedaily.cache;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import edu.sysuedaily.cachedatabase.ImageCacheDatabase;

public class ImageCache {
	private static ImageCache imageCache = null;
	
	private Context context = null;
	
	private ImageCache(Context context) {
		this.context = context;
	}
	
	public static ImageCache getImageCache(Context context) {
		if (imageCache == null) {
			imageCache = new ImageCache(context);
			return imageCache;
		}
		else {
			return imageCache;
		}
	}
	
	public boolean cacheImage(Bitmap image, String url) {
		ImageCacheDatabase imageCacheHelper = new ImageCacheDatabase(this.context);
		boolean success = imageCacheHelper.cacheImage(new Date(), url, image);
		return success;
	}
	
	public Bitmap getCacheImage(String url, int requireSize) {
		ImageCacheDatabase imageDatabase = new ImageCacheDatabase(this.context);
		Bitmap bitmap  = imageDatabase.getCacheImage(url, requireSize);
		if (bitmap != null) {
			return bitmap;
		}
		else {
			return null;
		}
	}
}
