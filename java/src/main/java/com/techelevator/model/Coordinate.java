package com.techelevator.model;

public class Coordinate {

    private int coordinate_id;
    private double lat;
    private double lon;

    public Coordinate() {}

    public Coordinate(int coordinate_id, double lat, double lon) {
        this.coordinate_id = coordinate_id;
        this.lat = lat;
        this.lon = lon;
    }

    public int getCoordinate_id() {
        return coordinate_id;
    }

    public void setCoordinate_id(int coordinate_id) {
        this.coordinate_id = coordinate_id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }
}
