package com.mmgo.cashlessmap.service;

import com.mmgo.cashlessmap.entity.Coordinate;
import com.mmgo.cashlessmap.entity.GoogleMapApiClient;
import com.mmgo.cashlessmap.entity.GoogleMapApiResponse;

import java.util.List;

public class GoogleMapApiService {
    public static GoogleMapApiResponse getDirection(List<Coordinate> coordinates) {
        GoogleMapApiClient googleMapApiClient = new GoogleMapApiClient();
        return googleMapApiClient.execute(coordinates);
    }
}
