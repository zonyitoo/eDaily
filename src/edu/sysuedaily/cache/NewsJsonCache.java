package edu.sysuedaily.cache;

import java.util.Date;

import edu.sysuedaily.cachedatabase.JsonCacheDatabase;
import android.content.Context;

public class NewsJsonCache extends BaseJsonCache{

	protected NewsJsonCache(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static NewsJsonCache getNewsJsonCache(Context context) {
		if (jsonCache == null) {
			jsonCache = new NewsJsonCache(context);
			return (NewsJsonCache) jsonCache;
		}
		else {
			return (NewsJsonCache) jsonCache;
		}
	}

	@Override
	public void cacheJson(String json) {
		// TODO Auto-generated method stub
		JsonCacheDatabase newsJsonCacheDatabase = new JsonCacheDatabase(context);
		newsJsonCacheDatabase.cacheNewsJson(new Date(), json);
	}

	@Override
	public String getCacheJson() {
		// TODO Auto-generated method stub
		//return null;
		JsonCacheDatabase newsJsonCacheDatabase = new JsonCacheDatabase(context);
		String json = newsJsonCacheDatabase.getNewsCacheJson();
		if (json != null) {
			return json;
		}
		else {
			return null;
		}
	}

}
