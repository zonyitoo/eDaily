package edu.sysuedaily.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.database.sqlite.SQLiteDatabase;

public class NewsDataGetter extends DataGetter {

	static final String API_ADDRESS = "";

	SQLiteDatabase newsDatabase;

	public NewsDataGetter(SQLiteDatabase newsdb) {
		super(SERVER_ADDR + API_ADDRESS);
		newsDatabase = newsdb;
	}

	@Override
	public void getListData(int page, int pageSize,
			final OnCompleteListener listener) {
		NewsAsyncGetter getter = new NewsAsyncGetter(new OnCompleteListener() {

			@Override
			public void onComplete(Object data) {
				JSONObject object = (JSONObject) data;

				ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
				/* TODO 处理返回的数据合成arrayList */

				listener.onComplete(arrayList);
			}
		});

		DataParams params = new DataParams();
		params.put("page", page);
		params.put("count", pageSize);

		getter.execute(params);
	}

	@Override
	public void getData(int id, OnCompleteListener listener) {
		// TODO Auto-generated method stub

	}

	class NewsAsyncGetter extends DataAsyncGetter {
		OnCompleteListener listener;

		public NewsAsyncGetter(OnCompleteListener listener) {
			this.listener = listener;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			listener.onComplete(result);

		}

	}

}
