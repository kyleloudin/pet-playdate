package com.techelevator.dao;

import com.techelevator.model.Pet;
import com.techelevator.model.PlayDate;

import java.time.LocalDate;
import java.util.List;

public interface PlayDateDao {

    List<PlayDate> findAll();

    List<PlayDate> findDatesByPetId(int petId);

    PlayDate getPlayDateById(int playDateId);

    boolean createPlayDate(int petId, int locationId, String scheduledDate);

    boolean deleteDate(int playDateId);

    int getUserIdFromPlayDateId(int playDateId);
}