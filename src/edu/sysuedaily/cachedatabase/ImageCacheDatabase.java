package edu.sysuedaily.cachedatabase;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import edu.sysuedaily.request.RequestUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.util.Base64;
import android.util.Log;

// TODO: 还是把图片存放在SD卡上把。。
public class ImageCacheDatabase extends SQLiteOpenHelper {

	public static final int VERSION = 1;
	public static final String NAME = "image_cache.db";
	public static final String TABLE_NAME = "image_cache";
	
	public static final String COLUMN_URL = "image_url";
	public static final String COLUMN_IMAGE_PATH = "image_path";
	public static final String COLUMN_CACHE_DATE = "cache_date";
	
	private static final String CREATE_DATABASE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + 
			BaseColumns._ID + " INT PRIMARY KEY, " + 
			COLUMN_URL + " TEXT NOT NULL, " + 
			COLUMN_CACHE_DATE + " TEXT NOT NULL, " +
			COLUMN_IMAGE_PATH + " TEXT NOT NULL " + ");";
	
	public ImageCacheDatabase(Context context) {
		super(context, TABLE_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_DATABASE_SQL);
		//SQLiteDatabase.openOrCreateDatabase(file, factory)
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * cache a image
	 * @param date, cache a image
	 * @param url, the image url
	 * @param bitmap, the image
	 * @return true if cache success
	 */
	public boolean cacheImage(Date date, String url, Bitmap bitmap) {
		String dateString = String.valueOf(date.getTime());
		String encodeURL = Base64.encodeToString(url.getBytes(), Base64.DEFAULT).trim();
		String cachePath = CacheConstant.CACHE_IMAGE_DIR + encodeURL;
		// put it into database
		ContentValues values = new ContentValues();
		values.put(COLUMN_CACHE_DATE, dateString);
		values.put(COLUMN_IMAGE_PATH, cachePath);
		values.put(COLUMN_URL, encodeURL); // store the encode url in the database
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(TABLE_NAME, null, values);
		db.close();
		// write it into sdcard
		FileOutputStream fouts = CacheUtil.getFileOutputStream(cachePath);
		BufferedOutputStream bos = new BufferedOutputStream(fouts);
		boolean success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
		try {
			bos.close();
			fouts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * get a cache image for specify url
	 * @param url the image url
	 * @return the bitmap, or null if raise a fucking error
	 */
	public Bitmap getCacheImage(String url) {
		String encodeURL = Base64.encodeToString(url.getBytes(), Base64.DEFAULT).trim();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, COLUMN_URL + "=" + "'" + encodeURL + "'", 
				null, null, null, COLUMN_CACHE_DATE + " DESC");
		cursor.moveToFirst();
		try {
			int pathIndex = cursor.getColumnIndex(COLUMN_IMAGE_PATH);
			if (pathIndex != -1) {
				Log.d("Ragnarok", "pathIndex = " + pathIndex + ", size = " + cursor.getCount());
				String cachePath = cursor.getString(pathIndex);
				Log.d("Ragnarok", "cachePath = " + cachePath);
				cursor.close();
				db.close();
				FileInputStream fins = CacheUtil.getFileInputStream(cachePath);
				Bitmap bitmap = BitmapFactory.decodeStream(fins);
				Log.d("Ragnarok", "get the cache bitmap");
				return bitmap;
			} else {
				Log.d("Ragnarok", "index = -1");
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cursor.close();
			db.close();
			return null;
		}
	}

}
