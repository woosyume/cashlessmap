package com.mmgo.cashlessmap.utility;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mmgo.cashlessmap.entity.Coordinate;

import java.util.Arrays;
import java.util.List;

public class CoordinateParser {
    public static List<Coordinate> parse(String json) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        double fromLatitude = jsonObject.get("fromLatitude").getAsDouble();
        double fromLongitude = jsonObject.get("fromLongitude").getAsDouble();
        Coordinate from = new Coordinate(fromLatitude, fromLongitude);

        double toLatitude = jsonObject.get("toLatitude").getAsDouble();
        double toLongitude = jsonObject.get("toLongitude").getAsDouble();
        Coordinate to = new Coordinate(toLatitude, toLongitude);

        return Arrays.asList(from, to);
    }
}
