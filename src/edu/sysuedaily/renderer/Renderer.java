package edu.sysuedaily.renderer;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;

public abstract class Renderer<T> {
	
	
	public abstract T renderToObject();
	
	public abstract ArrayList<T> renderToList();
	
	public abstract T jsonToObject(JSONObject jsonObject);
}
