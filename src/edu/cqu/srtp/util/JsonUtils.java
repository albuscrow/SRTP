package edu.cqu.srtp.util;

import java.lang.reflect.Type;

import android.util.Log;

import com.google.gson.Gson;

public class JsonUtils {
	private static final String TAG = "JsonUtils";
	private static final String PARSE_ERROR = "json prase error";
	public static Object parseJsonResult(Type type,String json){
		Gson gson = new Gson();
		Object result = null;
		try {
			result = gson.fromJson(json, type);
		} catch (Exception e) {
			Log.e(TAG, PARSE_ERROR);
			e.printStackTrace();
			result = null;
		}
		return result;
	}
}
