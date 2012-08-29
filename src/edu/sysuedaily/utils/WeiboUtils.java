package edu.sysuedaily.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;
import com.weibo.net.WeiboParameters;


public class WeiboUtils {
	public static final String CONSUMER_KEY = "2765470881";
	public static final String CONSUMER_SECRET = "3ffd8b451c2ec938d57468cb8515a46a";
	
	public static final String ACCESS_TOKEN = "access_token";
	public static final String EXPIRES_IN = "expires_in";
	public static final String USER_SCREEN_NAME = "user_screen_name";
	
	
	public static void authorize(final Activity activity, final WeiboDialogListener listener) {
		final Weibo weibo = Weibo.getInstance();
		weibo.setRedirectUrl("https://api.weibo.com/oauth2/default.html");
		weibo.authorize(activity, listener);
	}
	
	public static JSONObject getUserDetail(Activity activity) {
		String urlshow = Weibo.SERVER + "users/show.json";
		String getUid = Weibo.SERVER + "account/get_uid.json";
		
		JSONObject retJSON = null;
		
		Weibo weibo = Weibo.getInstance();
		try {
			WeiboParameters bundle = new WeiboParameters();
			bundle.add("source", Weibo.getAppKey());
			String ret = weibo.request(activity, getUid, bundle, "GET", weibo.getAccessToken());
			retJSON = new JSONObject(ret);
			String uid = retJSON.getString("uid");
			
			bundle = new WeiboParameters();
			bundle.add("source", Weibo.getAppKey());
			bundle.add("uid", uid);
			ret = weibo.request(activity, urlshow, bundle, "GET", weibo.getAccessToken());
			retJSON = new JSONObject(ret);
			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return retJSON;
	}
	
	public static JSONObject getUserTimeline(Activity activity, String uid) {
		JSONObject ret = null;
		
		String url = Weibo.SERVER + "statuses/user_timeline.json";
        WeiboParameters bundle = new WeiboParameters();
        bundle.add("source", Weibo.getAppKey());
        bundle.add("uid", uid);
        Weibo weibo = Weibo.getInstance();
        try {
			String rlt = weibo.request(activity, url, bundle, "GET", weibo.getAccessToken());
			ret = new JSONObject(rlt);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
}
