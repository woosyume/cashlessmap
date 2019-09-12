package com.mmgo.cashlessmap.entity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.utility.Option;

import io.grpc.internal.IoUtils;

public class GurunaviApiClient {
	

	private Gson gson = new Gson();
	
	private String credential = "e7295aa012ca9b21408cca91e1aa32f4"; // TODO property
	
	public Stores execute(Option option) throws JsonSyntaxException, ParseException, IOException, HttpException {
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(findStore(option));) {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("StatusCode: " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                return getJsonValue(parseText(response.getEntity()));
            } else {
				return this.processNotFoundResult(parseText(response.getEntity()));
            }
        }
    }
	
	private HttpGet findStore(Option option) {
    	URIBuilder builder = null;
		try {
			builder = new URIBuilder("https://api.gnavi.co.jp/RestSearchAPI/v3/");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	builder.setParameter("keyid", credential)
    	.setParameter("latitude", option.latitude)
    	.setParameter("longitude",option.longitude)
    	.setParameter("e_money",option.eMoney.toString())
    	.setParameter("lunch",option.lunch.toString())
    	.setParameter("no_smoking",option.noSmoking.toString())
      	.setParameter("card", option.card.toString())
      	.setParameter("hit_per_page", option.hitPerPage.toString())
    	.setParameter("range",option.range)
    	.setParameter("freeword",option.translatedSeachText);
    	try {
			return new HttpGet(builder.build());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
	private String parseText(HttpEntity entity) throws JsonSyntaxException, ParseException, IOException {
		String response;
		InputStream istream = entity.getContent();
		byte[] bytes = IoUtils.toByteArray(istream);
		response = new String(bytes, "UTF-8");
		return response;
    }
	
	private Stores getJsonValue(String response) {
		JsonObject object = gson.fromJson(response, JsonObject.class);
		Stores stores = new Stores();
		for(int i = 0 ; i < object.getAsJsonArray("rest").size() ; i++) {
			Store store = new Store();
			store.name = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("name").getAsString();
			store.prShort = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("pr").getAsJsonObject().get("pr_short").getAsString();
			store.prLong = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("pr").getAsJsonObject().get("pr_long").getAsString();
			store.creditCard = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("credit_card").getAsString();
			store.eMoney = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("e_money").getAsString();
			store.latitude = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("latitude").getAsString();
			store.longitude = object.getAsJsonArray("rest").get(i).getAsJsonObject().get("longitude").getAsString();
			stores.add(store);
		}        
        return stores;
	}
	
	private Stores processNotFoundResult(String response) {
		System.out.println(response);
        return new Stores();
	}
}
