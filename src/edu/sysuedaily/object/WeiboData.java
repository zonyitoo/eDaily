package edu.sysuedaily.object;

import java.util.Date;

public class WeiboData {
	public Date createdAt = null;
	public int id = 0;
	public String text = null;
	public String source = null;
	public int inReplyToStatusId = 0;
	public int inReplyToUserId = 0;
	public String inReplyToScreenName = null;
	public String geo = null;
	public int repostsCount = 0;
	public int commentsCount = 0;
	public WeiboUser user = null;
	public WeiboData retweetStatus = null;
	public String bmiddlePic = null;
	public String originalPic = null;
	public String thumbnailPic = null;

	public class WeiboUser {
		public String screenName = null;
		public int id = 0;
		public String name = null;
		public String province = null;
		public String city = null;
		public String location = null;
		public String description = null;
		public String url = null;
		public String profileImageUrl = null;
		public String domain = null;
		public String gender = null;
		public int followersCount = 0;
		public int friendsCount = 0;
		public int statusesCount = 0;
		public int favouritesCount = 0;
		public Date createAt = null;
		public boolean following = false;
		public String avatarLarge = null;
		public boolean followMe = false;
		public int biFollowersCount = 0;
	}
}
