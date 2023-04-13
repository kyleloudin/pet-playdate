package com.techelevator.dao;

import com.techelevator.model.Coordinate;

import java.util.List;

public interface CoordinateDao {

    List<Coordinate> findAll();

    Coordinate getCoordinateById(int coordinate_id);

    Coordinate findCoordinateByType(int coordinate_id);

    boolean create(int coordinate_id, double lat, double lon);

    boolean updateCoordinate(Coordinate coordinate);

    boolean deleteCoordinate(int coordinate_id);

}