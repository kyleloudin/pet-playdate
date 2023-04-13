package com.techelevator.model;

import java.math.BigDecimal;

public class Location {
//todo - figure out how coordinate object lat/lon and Location object lat/lon interact

    private int location_id;
    private String park_name;
    private String city;
    private String gps;
    private BigDecimal lat;
    private BigDecimal lon;
    private String location_type;
    private boolean off_leash;
    private String site_description;
    private String website;

    public Location() {}

    public Location(int location_id, String park_name, String city, String gps, BigDecimal lat, BigDecimal lon, String location_type, boolean off_leash, String site_description, String website) {
        this.location_id = location_id;
        this.park_name = park_name;
        this.city = city;
        this.gps = gps;
        this.lat = lat;
        this.lon = lon;
        this.location_type = location_type;
        this.off_leash = off_leash;
        this.site_description = site_description;
        this.website = website;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocationID(int location_id) {
        this.location_id = location_id;
    }

    public String getParkName() { return park_name;}

    public void setParkName(String park_name) { this.park_name = park_name; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getGps() { return gps; }

    public void setGps(String gps) { this.gps = gps; }

    public BigDecimal getLat() { return lat; }

    public void setLat(BigDecimal lat) { this.lat = lat; }

    public BigDecimal getLon() { return lon; }

    public void setLon(BigDecimal lon) { this.lon = lon; }

    public String getType() { return location_type; }

    public void setType(String type) { this.location_type = type; }

    public boolean isOffLeash() { return off_leash; }

    public void setOffLeash(boolean off_leash) { this.off_leash = off_leash; }

    public String getSiteDescription () { return site_description; }

    public void setSiteDescription(String siteDescription) { this.site_description = siteDescription; }

    public String getWebsite() { return website; }

    public void setWebsite(String website) { this.website = website; }
}
