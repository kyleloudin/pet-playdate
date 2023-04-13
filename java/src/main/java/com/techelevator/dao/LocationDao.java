package com.techelevator.dao;

import com.techelevator.model.Coordinate;
import com.techelevator.model.Location;

import java.math.BigDecimal;
import java.util.List;

public interface LocationDao {

    List<Location> getPlayLocations();

    Location getLocationByLocationID(int location_id);

    Location findLocationByType(String type);

    boolean create(int location_id, String park_name, String city, String gps, BigDecimal lat, BigDecimal lon, String location_type, boolean off_leash, String site_description, String website);

    boolean updateLocation(Location location);

    boolean deleteLocation(int location_id);
}
