package edu.sysuedaily.renderer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import edu.sysuedaily.object.WeiboData;

public class WeiboCacheRenderer extends CacheRenderer<WeiboData> {

	private static final SimpleDateFormat CREATE_DATE_FORMATER = new SimpleDateFormat(
			"EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH);

	@Override
	public WeiboData renderToObject() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<WeiboData> renderToList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public WeiboData jsonToObject(JSONObject jsonObject) {
		WeiboData weiboData = new WeiboData();
		try {
			weiboData.createdAt = CREATE_DATE_FORMATER.parse(jsonObject
					.getString("created_at"));
			weiboData.id = jsonObject.getInt("id");
			weiboData.text = jsonObject.getString("text");
			weiboData.source = jsonObject.getString("source");
			weiboData.inReplyToStatusId = jsonObject
					.getInt("in_reply_to_status_id");
			weiboData.inReplyToUserId = jsonObject
					.getInt("in_reply_to_user_id");
			weiboData.inReplyToScreenName = jsonObject
					.getString("in_reply_to_screen_name");

			weiboData.geo = null;

			weiboData.repostsCount = jsonObject.getInt("reposts_count");
			weiboData.commentsCount = jsonObject.getInt("comments_count");

			weiboData.user = weiboData.new WeiboUser();
			JSONObject userObject = jsonObject.getJSONObject("user");
			weiboData.user.id = userObject.getInt("id");
			weiboData.user.screenName = userObject.getString("screen_name");
			weiboData.user.name = userObject.getString("name");
			weiboData.user.province = userObject.getString("province");
			weiboData.user.city = userObject.getString("city");
			weiboData.user.location = userObject.getString("location");
			weiboData.user.description = userObject.getString("description");
			weiboData.user.url = userObject.getString("url");
			weiboData.user.profileImageUrl = userObject
					.getString("profile_image_url");
			weiboData.user.domain = userObject.getString("domain");
			weiboData.user.gender = userObject.getString("gender");
			weiboData.user.followersCount = userObject
					.getInt("followers_count");
			weiboData.user.statusesCount = userObject.getInt("statuses_count");
			weiboData.user.favouritesCount = userObject
					.getInt("favourites_count");
			weiboData.user.createAt = CREATE_DATE_FORMATER.parse(userObject
					.getString("created_at"));
			weiboData.user.following = userObject.getBoolean("following");
			weiboData.user.avatarLarge = userObject.getString("avatar_large");
			weiboData.user.followMe = userObject.getBoolean("follow_me");
			weiboData.user.biFollowersCount = userObject
					.getInt("bi_followers_count");
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getRenderJson() {
		// TODO 自动生成的方法存根
		return null;
	}

}
