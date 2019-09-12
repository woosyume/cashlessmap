package com.mmgo.cashlessmap.entity;

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return this.latitude + "," + this.longitude;
    }
}
