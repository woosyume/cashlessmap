package com.mmgo.cashlessmap.entity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.utility.Option;
import org.springframework.beans.factory.annotation.Autowired;

import io.grpc.internal.IoUtils;

public class GurunaviApiClient {
	

	private Gson gson = new Gson();
	
	private String credential = "e7295aa012ca9b21408cca91e1aa32f4";
	
	public List<Store> execute(Option option) throws JsonSyntaxException, ParseException, IOException, HttpException {
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(findStore(option));) {
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("StatusCode: " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                return firstJsonValue(parseText(response.getEntity()));
            } else {
                throw new HttpException(Integer.toString(statusCode));
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
    	builder.setParameter("keyid", credential).setParameter("latitude", option.latitude).setParameter("longitude",option.longitude)
    	.setParameter("range",option.range);
    	try {
    		System.out.println(new HttpGet(builder.build()));
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
	
	private List<Store> firstJsonValue(String response) {
		System.out.println("JSON>>");
		System.out.println(response);
		System.out.println("<<JSON");
		
		JsonObject object = gson.fromJson(response, JsonObject.class);
		List<Store> list = new ArrayList<>();
		for(int i = 0 ; i < object.getAsJsonArray("rest").size() ; i++) {
			Store store = new Store();
			store.name = object
					.getAsJsonArray("rest")
					.get(i)
					.getAsJsonObject()
					.get("name")
					.getAsString();
			store.latitude = object
					.getAsJsonArray("rest")
					.get(i)
					.getAsJsonObject()
					.get("latitude")
					.getAsString();
			store.longitude = object
					.getAsJsonArray("rest")
					.get(i)
					.getAsJsonObject()
					.get("longitude")
					.getAsString();
			list.add(store);
		}
        
        return list;
	}
}
