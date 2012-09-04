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

public class NewsCacheDatabase extends BaseCacheDatabase {
	
	private static final int VERSION = 1;
	private static final String NAME = "news_cache.db";
	private static final String TABLE_NAME = "news_cache";
	
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_JSON = "json";

	private static final String CREATE_DATABASE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " +
			BaseColumns._ID + " INT PRIMARY KEY, " + 
			COLUMN_DATE + " TEXT NOT NULL , " + 		
			COLUMN_JSON + " TEXT NOT NULL " + ");";
	
	public NewsCacheDatabase(Context context) {
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
	
	public String getCacheJson() {
		//String dateString = new SimpleDateFormat("yyyy/MM/dd").format(date);
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(
				TABLE_NAME, null, null, null, null, null, COLUMN_DATE + " DESC");
		cursor.moveToLast();
		int jsonIndex = cursor.getColumnIndex(COLUMN_JSON);
		return cursor.getString(jsonIndex);
	}
	
	public void cacheJson(Date date, String json) {
		String dateString = String.valueOf(date.getTime());
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_DATE, dateString);
		contentValues.put(COLUMN_JSON, json);
		database.insert(TABLE_NAME, null, contentValues);
	}
	
	

}
