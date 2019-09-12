package com.mmgo.cashlessmap.entity;

public class Coordinate {
    private double latitude;
    private double longitutde;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitutde = longitude;
    }

    public String toString() {
        return this.latitude + "," + this.longitutde;
    }
}
