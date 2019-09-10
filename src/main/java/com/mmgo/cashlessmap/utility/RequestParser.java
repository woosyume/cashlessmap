package com.mmgo.cashlessmap.utility;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RequestParser {
	public static Option parse(String text) {
		Option option = new Option();
		JsonObject jsonObj = (JsonObject) new Gson().fromJson(text, JsonObject.class);
		//Payの処理追加
		option.latitude = jsonObj.get("latitude").getAsString();
		option.longitude = jsonObj.get("longitude").getAsString();
		return option;
	}
}
