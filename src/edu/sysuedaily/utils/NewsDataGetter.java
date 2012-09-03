package edu.sysuedaily.utils;

import org.json.JSONObject;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

public class NewsDataGetter extends DataGetter {

	static final String API_ADDRESS = "";
	
	SQLiteDatabase newsDatabase;
	
	public NewsDataGetter(SQLiteDatabase newsdb) {
		newsDatabase = newsdb;
	}
	
	@Override
	public void getListData(int page, int pageSize, OnCompleteListener listener) {
		// TODO Auto-generated method stub
		
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
			// TODO Auto-generated method stub
			
			
		}
		
	}

}
