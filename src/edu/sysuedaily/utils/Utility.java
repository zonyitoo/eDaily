package edu.sysuedaily.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public class Utility {
	
	static final String AT_PATTERN_STRING = "@[\\u4e00-\\u9fa5\\w\\-]+";
	static final Pattern AT_PATTERN = Pattern.compile(AT_PATTERN_STRING);
	static final String TREND_PATTERN_STRING = "#([^\\#|.]+)#";
	static final Pattern TREND_PATTERN = Pattern.compile(TREND_PATTERN_STRING);
	
	public static SpannableString markWeiboAtAndTrend(String text) {
		SpannableString spannableString = new SpannableString(text);
		
		Matcher atmatcher = AT_PATTERN.matcher(text);
		while (atmatcher.find()) {
			// textSpannableString.setSpan(new StyleSpan(
			// android.graphics.Typeface.BOLD), matcher.start(), matcher
			// .end(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
			spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),
					atmatcher.start(), atmatcher.end(),
					SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
		}
		
		Matcher trendMatcher = TREND_PATTERN.matcher(text);
		while (trendMatcher.find()) {
			spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),
					trendMatcher.start(), trendMatcher.end(),
					SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
		}
		
		return spannableString;
	}
	
}
