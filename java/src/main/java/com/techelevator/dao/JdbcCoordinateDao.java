//package com.techelevator.dao;
//
//import com.techelevator.model.Coordinate;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//// Copied directly from JdbcUserDao and changed a few words from User to Pet
//
//@Component
//public abstract class JdbcCoordinateDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcCoordinateDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Coordinate getCoordinateById(int coordinate_id) {
//        String sql = "SELECT * FROM location WHERE location_id = ?";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, coordinate_id);
//        if (results.next()) {
//            return mapRowToCordinate(results);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Coordinate> findAll() {
//        List<Coordinate> coordinate = new ArrayList<>();
//        String sql = "select * from location";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while (results.next()) {
//            Coordinate coordinate = mapRowToCoordinate(results);
//            coordinate.add(coordinate);
//        }
//
//        return coordinate;
//    }
//
//    @Override
//    public Coordinate findCoordinateByType(String locationType) {
//        if (type == null) throw new IllegalArgumentException("Please choose a type of activity.");
//
//        for (Coordinate coordinate : this.findAll()) {
//            if (coordinate.getLocationType().equalsIgnoreCase(locationType)) {
//                return coordinate;
//            }
//        }
//        throw new UsernameNotFoundException("User " + locationType + " was not found.");
//    }
//
//
//    public boolean createCoordinate(int coordinate_id, int lat, int lon) {
//        String insertUserSql = "insert into location (location_id, park_name, city, gps, lat, long, type, off_leash, description, website) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//        return jdbcTemplate.update(insertUserSql, park_name, city, gps, lat, long, type, off_leash, description, website) == 1;
//    }
//
//    @Override
//    public boolean deletePet(int petId) {
//        String sql = "DELETE FROM pet WHERE pet_id = ?;";
//        jdbcTemplate.update(sql, petId);
//        return true;
//    }
//
//    private Coordinate mapRowToCoordinate(SqlRowSet rs) {
//        Coordinate coordinate = new Coordinate();
//        coordinate.setCoordinate_id(results.getInt("coordinate_id"));
//
//        return coordinate;
//    }
//}
