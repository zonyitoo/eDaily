package edu.sysuedaily.cachedatabase;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseCacheDatabase extends SQLiteOpenHelper{

	public BaseCacheDatabase(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public abstract String getCacheJson();
	public abstract void cacheJson(Date date, String json);

}
