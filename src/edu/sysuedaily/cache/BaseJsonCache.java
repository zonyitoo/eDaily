package edu.sysuedaily.cache;

import android.content.Context;

public abstract class BaseJsonCache {

	protected static Context context = null;
	protected static BaseJsonCache jsonCache = null;
	
	protected BaseJsonCache(Context context) {
		this.context = context;
	}
	
	/**
	 * cache the json
	 * @param json
	 */
	public abstract void cacheJson(String json);
	
	/**
	 * get the newest cache json
	 * if raise some error, just simply return null
	 * @return
	 */
	public abstract String getCacheJson();
}
