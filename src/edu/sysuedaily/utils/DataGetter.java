package edu.sysuedaily.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public abstract class DataGetter {

	abstract public void getListData(int page, int pageSize,
			OnCompleteListener listener);

	abstract public void getData(int id, OnCompleteListener listener);

	public interface OnCompleteListener {
		public void onComplete(Object data);
	}

	protected class DataParams {

		private URL url;
		private JSONObject params;

		public DataParams(String url, JSONObject params)
				throws MalformedURLException {
			this.url = new URL(url);
			this.params = params;
		}

		public URL getURL() {
			return url;
		}

		public JSONObject getParams() {
			return params;
		}
	}

	abstract protected class DataAsyncGetter extends
			AsyncTask<DataParams, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(DataParams... params) {
			JSONObject result = null;
			try {
				URL url = params[0].getURL();
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
						+ URLEncoder.encode(params[0].getParams().toString()));
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
