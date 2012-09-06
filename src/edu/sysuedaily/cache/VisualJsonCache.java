package edu.sysuedaily.cache;

import java.util.Date;

import android.content.Context;
import edu.sysuedaily.cachedatabase.JsonCacheDatabase;

public class VisualJsonCache extends BaseJsonCache {

	protected VisualJsonCache(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static VisualJsonCache getVisualJsonCache(Context context) {
		if (jsonCache == null) {
			jsonCache = new VisualJsonCache(context);
			return (VisualJsonCache) jsonCache;
		}
		else {
			return (VisualJsonCache) jsonCache;
		}
	}

	@Override
	public void cacheJson(String json) {
		// TODO Auto-generated method stub
		JsonCacheDatabase altitudeJsonCacheDatabase = new JsonCacheDatabase(context);
		altitudeJsonCacheDatabase.cacheAltitudeJson(new Date(), json);
	}

	@Override
	public String getCacheJson() {
		// TODO Auto-generated method stub
		//return null;
		JsonCacheDatabase altitudeJsonCacheDatabase = new JsonCacheDatabase(context);
		String json = altitudeJsonCacheDatabase.getAltitudeCacheJson();
		if (json != null) {
			return json;
		}
		else {
			return null;
		}
	}

}
