package com.mmgo.cashlessmap.utility;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class RequestParser {
	public static Option parse(String text) {
		Option option = new Option();
		JsonObject jsonObj = new Gson().fromJson(text, JsonObject.class);

		option.eMoney = getEMoneyAsInt(jsonObj);
		option.pay = getPayAsList(jsonObj);
		option.lang = jsonObj.get("lang").getAsString();
		option.card = jsonObj.get("card").getAsInt();		
		option.lunch = jsonObj.get("lunch").getAsInt();
		option.noSmoking = jsonObj.get("nosmoking").getAsInt();
		option.latitude = jsonObj.get("latitude").getAsString();
		option.longitude = jsonObj.get("longitude").getAsString();
		// option.seachText = jsonObj.get("searchText").getAsString(); TODO keywordSearch on frontend
		return option;
	}

	public static Option getOptionWithParsedJsonForInternational(String text) {
		Option option = new Option();
		JsonObject jsonObj = new Gson().fromJson(text, JsonObject.class);

		option.lang = jsonObj.get("lang").getAsString();
		option.storeId = jsonObj.get("storeId").getAsString();		
		return option;
	}

	private static List<String> getPayAsList(JsonObject jsonObject) {
		List<String> pay = new ArrayList<String>();
		for(JsonElement element : jsonObject.getAsJsonArray("pay")) {
			pay.add(element.getAsString());
		}
		return pay;
	}

	private static Integer getEMoneyAsInt(JsonObject jsonObject) {
		if(jsonObject.getAsJsonArray("pay").size() == 0) {
			return 0;
		} else {
			return 1;
		}
	}

}
