package com.mmgo.cashlessmap.utility;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RequestParser {
	public static Option parse(String text) {
		Option option = new Option();
		JsonObject jsonObj = (JsonObject) new Gson().fromJson(text, JsonObject.class);
		if(jsonObj.getAsJsonArray("pay").size() == 0) {
			option.eMoney = 0;
		}else {
			option.eMoney = 1;
			for(int i = 0 ; i < jsonObj.getAsJsonArray("pay").size() ; i++) {
				option.pay.add(jsonObj.getAsJsonArray("pay").get(i).getAsString());
			}
		}
		//Payの処理追
		option.lang = jsonObj.get("lang").getAsString();	
		option.card = jsonObj.get("card").getAsInt();		
		option.lunch = jsonObj.get("lunch").getAsInt();
		option.noSmoking = jsonObj.get("nosmoking").getAsInt();
		option.latitude = jsonObj.get("latitude").getAsString();
		option.longitude = jsonObj.get("longitude").getAsString();
		option.seachText = jsonObj.get("seachText").getAsString();
		return option;
	}
	
}
