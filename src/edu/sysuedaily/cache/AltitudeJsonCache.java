package edu.sysuedaily.cache;

import java.util.Date;

import edu.sysuedaily.cachedatabase.JsonCacheDatabase;
import android.content.Context;

public class AltitudeJsonCache extends BaseJsonCache {

	protected AltitudeJsonCache(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static AltitudeJsonCache getAltitudeJsonCache(Context context) {
		if (jsonCache != null) {
			return (AltitudeJsonCache) jsonCache;
		}
		else {
			jsonCache = new AltitudeJsonCache(context);
			return (AltitudeJsonCache) jsonCache;
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
