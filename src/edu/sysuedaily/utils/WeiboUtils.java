package edu.sysuedaily.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.weibo.net.DialogError;
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
	
	public static final int AUTHORIZE_EXCEPTION = 0;
	public static final int AUTHORIZE_ERROR = 1;
	public static final int AUTHORIZE_COMPLETE = 2;
	public static final int AUTHORIZE_CANCEL = 3;
	
	
	public static void authorize(final Activity activity, final Handler handler) {
		final Weibo weibo = Weibo.getInstance();
		weibo.setupConsumerConfig(WeiboUtils.CONSUMER_KEY, WeiboUtils.CONSUMER_SECRET);
		weibo.setRedirectUrl("https://api.weibo.com/oauth2/default.html");
		weibo.authorize(activity, new WeiboDialogListener() {
			
			@Override
			public void onWeiboException(WeiboException e) {
				Bundle bundle = new Bundle();
				bundle.putString("message", e.getMessage());
				Message message = new Message();
				message.setData(bundle);
				message.what = AUTHORIZE_EXCEPTION;
				handler.sendMessage(message);
			}
			
			@Override
			public void onError(DialogError e) {
				Bundle bundle = new Bundle();
				bundle.putString("message", e.getMessage());
				Message message = new Message();
				message.setData(bundle);
				message.what = AUTHORIZE_ERROR;
				handler.sendMessage(message);
			}
			
			@Override
			public void onComplete(final Bundle values) {
				final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
				
				new Thread() {

					@Override
					public void run() {
						String token = values.getString("access_token");
						String expiresin = values.getString("expires_in");
						prefs.edit()
							.putString(WeiboUtils.ACCESS_TOKEN, token)
							.putString(WeiboUtils.EXPIRES_IN, expiresin)
							.commit();
						
						JSONObject userdetail = WeiboUtils.getUserDetail(activity);
						String username = prefs.getString(WeiboUtils.USER_SCREEN_NAME, "WRONGNAME");
						try {
							username = userdetail.getString("screen_name");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NullPointerException e) {
							// TODO: handle exception
						}
						prefs.edit().putString(WeiboUtils.USER_SCREEN_NAME, username).commit();
						
						
						Message message = new Message();
						message.what = AUTHORIZE_COMPLETE;
						Bundle bundle = new Bundle();
						bundle.putString(ACCESS_TOKEN, token);
						bundle.putString(EXPIRES_IN, expiresin);
						handler.sendMessage(message);
					}
					
				}.start();
			}
			
			@Override
			public void onCancel() {
				Message message = new Message();
				message.what = AUTHORIZE_CANCEL;
				handler.sendMessage(message);
			}
		});
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
	
	public static JSONObject getUserTimeline(Activity activity, String uid, int page) {
		JSONObject ret = null;
		
		String url = Weibo.SERVER + "statuses/user_timeline.json";
        WeiboParameters bundle = new WeiboParameters();
        bundle.add("source", Weibo.getAppKey());
        bundle.add("uid", uid);
        bundle.add("page", Integer.toString(page));
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
	
	public static JSONObject showWeibo(Activity activity, String weiboid) {
		JSONObject ret = null;
		String url = Weibo.SERVER + "statused/show.json";
		WeiboParameters bundle = new WeiboParameters();
		bundle.add("source", Weibo.getAppKey());
		bundle.add("id", weiboid);
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
	
	class GetUserDetailThread extends Thread {
		
		Activity activity;
		SharedPreferences prefs;
		public GetUserDetailThread(Activity activity, SharedPreferences prefs) {
			this.activity = activity;
			this.prefs = prefs;
		}
		
		@Override
		public void run() {
			JSONObject userdetail = WeiboUtils.getUserDetail(activity);
			String username = prefs.getString(WeiboUtils.USER_SCREEN_NAME, "WRONGNAME");
			try {
				username = userdetail.getString("screen_name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			prefs.edit().putString(WeiboUtils.USER_SCREEN_NAME, username).commit();
		}
		
	}
	
}
