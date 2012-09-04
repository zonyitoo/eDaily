package edu.sysuedaily.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public abstract class DataGetter {
	
	protected static String SERVER_ADDR = "";
	
	URL url;
	
	public DataGetter(String urlstr) {
		url = null;
		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract public void getListData(int page, int pageSize,
			OnCompleteListener listener);

	abstract public void getData(int id, OnCompleteListener listener);

	public interface OnCompleteListener {
		public void onComplete(Object data);
	}

	protected class DataParams {
		JSONObject jsonObject;
		
		public DataParams() {
			jsonObject = new JSONObject();
		}
		
		public void put(String key, Object value) {
			try {
				jsonObject.put(key, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public JSONObject getJSONObject() {
			return jsonObject;
		}
	}

	abstract protected class DataAsyncGetter extends
			AsyncTask<DataParams, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(DataParams... params) {
			JSONObject result = null;
			try {
				
				HttpsURLConnection connection = (HttpsURLConnection) url
						.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-type", "text/json");

				connection.connect();

				PrintWriter writer = new PrintWriter(
						connection.getOutputStream());
				writer.print("data="
						+ URLEncoder.encode(params[0].getJSONObject().toString()));
				writer.flush();

				InputStreamReader reader = new InputStreamReader(
						connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(reader);
				String ret = bufferedReader.readLine();
				result = new JSONObject(ret);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		@Override
		abstract protected void onPostExecute(JSONObject result);

	}
}
