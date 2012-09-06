package edu.sysuedaily.cachedatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * This class contains all json cache operations,
 * it include two operations, cache the json and get the cache json
 * if raise some error when get the cache json, just simply return null;
 * @author ragnarok
 *
 */
public class JsonCacheDatabase extends SQLiteOpenHelper {
	
	private static final int VERSION = 1;
	private static final String NAME = "json_cache.db";
	private static final String TABLE_NAME = "json_cache";
	
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_JSON = "json";
	public static final String COLUMN_TYPE = "type";
	
	public static final String NEWS_CACHE = "news_cache";
	public static final String ALTITUDE_CACHE = "altitude_cache";
	//public static final String COMMENT_CACHE = "comment_cache";
	public static final String VISUAL_CACHE = "visual_cache";
	public static final String WEIBO_CACHE = "weibo_cache";

	private static final String CREATE_DATABASE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " +
			BaseColumns._ID + " INT PRIMARY KEY, " + 
			COLUMN_DATE + " TEXT NOT NULL , " + 	
			COLUMN_TYPE + " TEXT NOT NULL , " +
			COLUMN_JSON + " TEXT NOT NULL " + ");";
	
	public JsonCacheDatabase(Context context) {
		super(context, NAME, null, VERSION);
		// TODO Auto-generated constructor stub
		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.openOrCreateDatabase(CacheConstant.CACHE_BASE_DIR + NAME, null);
		db.execSQL(CREATE_DATABASE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public String getNewsCacheJson() {
		//String dateString = new SimpleDateFormat("yyyy/MM/dd").format(date);
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(
				TABLE_NAME, null, COLUMN_TYPE + " = " + NEWS_CACHE, null, null, null, COLUMN_DATE + " DESC");
		//cursor.moveToLast();
		try {
			int jsonIndex = cursor.getColumnIndex(COLUMN_JSON);
			cursor.close();
			database.close();
			return cursor.getString(jsonIndex);
		} catch (Exception e) {
			cursor.close();
			database.close();
			return null;
		}
	}
	
	public void cacheNewsJson(Date date, String json) {
		String dateString = String.valueOf(date.getTime());
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_DATE, dateString);
		contentValues.put(COLUMN_JSON, json);
		contentValues.put(COLUMN_TYPE, NEWS_CACHE);
		database.insert(TABLE_NAME, null, contentValues);
		database.close();
	}
	
	public String getVisualCacheJson() {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(
				TABLE_NAME, null, COLUMN_TYPE + " = " + VISUAL_CACHE, null, null, null, COLUMN_DATE + " DESC");
		//cursor.moveToLast();
		try {
			int jsonIndex = cursor.getColumnIndex(COLUMN_JSON);
			cursor.close();
			database.close();
			return cursor.getString(jsonIndex);
		} catch (Exception e ) {
			cursor.close();
			database.close();
			return null;
		}
	}
	
	public void cacheVisualJson(Date date, String json) {
		String dateString = String.valueOf(date.getTime());
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_DATE, dateString);
		contentValues.put(COLUMN_JSON, json);
		contentValues.put(COLUMN_TYPE, VISUAL_CACHE);
		database.insert(TABLE_NAME, null, contentValues);
		database.close();
	}
	
	public String getAltitudeCacheJson() {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(
				TABLE_NAME, null, COLUMN_TYPE + " = " + ALTITUDE_CACHE, null, null, null, COLUMN_DATE + " DESC");
		//cursor.moveToLast();
		try {
			int jsonIndex = cursor.getColumnIndex(COLUMN_JSON);
			cursor.close();
			database.close();
			return cursor.getString(jsonIndex);
		} catch (Exception e) {
			cursor.close();
			database.close();
			return null;
		}
	}
	
	public void cacheAltitudeJson(Date date, String json) {
		String dateString = String.valueOf(date.getTime());
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_DATE, dateString);
		contentValues.put(COLUMN_JSON, json);
		contentValues.put(COLUMN_TYPE, ALTITUDE_CACHE);
		database.insert(TABLE_NAME, null, contentValues);
		database.close();
	}
	
	public String getWeiboCacheJson() {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(
				TABLE_NAME, null, COLUMN_TYPE + " = " + WEIBO_CACHE, null, null, null, COLUMN_DATE + " DESC");
		//cursor.moveToLast();
		try {
			int jsonIndex = cursor.getColumnIndex(COLUMN_JSON);
			cursor.close();
			database.close();
			return cursor.getString(jsonIndex);
		} catch (Exception e) {
			cursor.close();
			database.close();
			return null;
		}
	}
	
	public void cacheWeiboJson(Date date, String json) {
		String dateString = String.valueOf(date.getTime());
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_DATE, dateString);
		contentValues.put(COLUMN_JSON, json);
		contentValues.put(COLUMN_TYPE, WEIBO_CACHE);
		database.insert(TABLE_NAME, null, contentValues);
		database.close();
	}
}
