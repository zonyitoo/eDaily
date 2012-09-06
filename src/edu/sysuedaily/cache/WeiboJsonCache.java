package edu.sysuedaily.cache;

import java.util.Date;

import android.content.Context;
import edu.sysuedaily.cachedatabase.JsonCacheDatabase;

public class WeiboJsonCache extends BaseJsonCache {

	protected WeiboJsonCache(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static WeiboJsonCache getWeiboJsonCache(Context context) {
		if (jsonCache == null) {
			jsonCache = new WeiboJsonCache(context);
			return (WeiboJsonCache) jsonCache;
		}
		else {
			return (WeiboJsonCache) jsonCache;
		}
	}

	@Override
	public void cacheJson(String json) {
		// TODO Auto-generated method stub
		JsonCacheDatabase weiboJsonCacheDatabase = new JsonCacheDatabase(context);
		weiboJsonCacheDatabase.cacheWeiboJson(new Date(), json);
	}

	@Override
	public String getCacheJson() {
		// TODO Auto-generated method stub
		//return null;
		JsonCacheDatabase weiboJsonCacheDatabase = new JsonCacheDatabase(context);
		String json = weiboJsonCacheDatabase.getWeiboCacheJson();
		if (json != null) {
			return json;
		}
		else {
			return null;
		}
	}

}
