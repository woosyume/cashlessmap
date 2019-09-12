package com.mmgo.cashlessmap.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mmgo.cashlessmap.entity.Coordinate;
import com.mmgo.cashlessmap.entity.GoogleMapApiResponse;
import io.grpc.internal.IoUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

public class GoogleMapApiClient {

    private Gson gson = new Gson();

    public GoogleMapApiResponse execute(List<Coordinate> coodinates) {
        try (CloseableHttpResponse response = HttpClients.createDefault().execute(getDirection(coodinates));) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                return transformFrom(parseText(response.getEntity()));
            } else {
                return this.processNotFoundResult(parseText(response.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpUriRequest getDirection(List<Coordinate> coodinates) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder("https://maps.googleapis.com/maps/api/directions/json");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        builder.setParameter("origin", coodinates.get(0).toString());
        builder.setParameter("destination", coodinates.get(1).toString());
        builder.setParameter("key", "AIzaSyD3as-pI0qLXEte93YpO0MQ7cISB0jrf6Q");
        try {
            return new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseText(HttpEntity entity) throws JsonSyntaxException, ParseException, IOException {
        String response;
        InputStream inputStream = entity.getContent();
        byte[] bytes = IoUtils.toByteArray(inputStream);
        response = new String(bytes, "UTF-8");
        return response;
    }

    private GoogleMapApiResponse transformFrom(String response) {
        JsonObject object = gson.fromJson(response, JsonObject.class);
        GoogleMapApiResponse googleMapApiResponse = new GoogleMapApiResponse();

        JsonArray routes = object.get("routes").getAsJsonArray();
        googleMapApiResponse.duration = routes.get(0).getAsJsonObject().get("legs").getAsJsonArray().get(0).getAsJsonObject().get("duration").getAsJsonObject().get("text").getAsString();

        return googleMapApiResponse;
    }

    private GoogleMapApiResponse processNotFoundResult(String response) {
        return new GoogleMapApiResponse();
    }
}
