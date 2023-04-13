package com.techelevator.dao;

import com.techelevator.model.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLocationDao implements LocationDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcLocationDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> getPlayLocations() {
            List<Location> locations = new ArrayList<>();
        String sql = "SELECT * FROM location";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Location location = mapRowToLocation(results);
            locations.add(location);
        }
        return locations;
    }

    public Location getLocationByLocationID(int location_id) {
        String sql = "SELECT * FROM location WHERE location_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location_id);
        results.next();
        Location location = mapRowToLocation(results);
        return location;
    }

    public Location findLocationByType(String type) {
        String sql = "SELECT location_type FROM location WHERE type = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, type);
        results.next();
        Location location = mapRowToLocation(results);
        return location;
    }

    public boolean create (int location_id, String park_name, String city, String gps, BigDecimal lat, BigDecimal lon, String location_type, boolean off_leash, String siteDescription, String website) {
        String insertLocationSql = "INSERT INTO location (park_name, city, gps, lat, long, location_type, off_leash, site_description, website) values (?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insertLocationSql, park_name, city, gps, lat, lon, location_type, off_leash, siteDescription, website) == 1;
    }

    public boolean updateLocation(Location location) {
        String updateLocationSql = "UPDATE location SET(park_name = ?, city = ?, gps = ?, lat = ?, long = ?, location_type = ?, off_leash = ?, site_description = ?, website = ?) WHERE location_id = ?";
        return jdbcTemplate.update(updateLocationSql, location.getParkName(), location.getCity(), location.getGps(), location.getLat(), location.getLon(), location.getType(), location.isOffLeash(), location.getSiteDescription(), location.getWebsite()) == 1;
    }

    public boolean deleteLocation(int location_id) {
        String sql = "DELETE FROM location WHERE location_id = ?;";
        jdbcTemplate.update(sql, location_id);
        return true;
    }

    private Location mapRowToLocation(SqlRowSet results) {
        Location location = new Location();
        location.setLocationID(results.getInt("location_id"));
        location.setParkName(results.getString("park_name"));
        location.setCity(results.getString("city"));
        location.setGps(results.getString("gps"));
        location.setLat(results.getBigDecimal( "lat"));
        location.setLon(results.getBigDecimal("long"));
        location.setType(results.getString("location_type"));
        location.setOffLeash(results.getBoolean("off_leash"));
        location.setSiteDescription(results.getString("site_description"));
        location.setWebsite(results.getString("website"));

        return location;
    }
}
