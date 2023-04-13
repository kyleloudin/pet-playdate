package com.techelevator.dao;

import com.techelevator.model.Pet;
import com.techelevator.model.PlayDate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Copied directly from JdbcUserDao and changed a few words from User to Pet


@Component
public class JdbcPlayDateDao implements PlayDateDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcPlayDateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayDate> findAll() {
        List<PlayDate> playDates = new ArrayList<>();
        String sql = "SELECT * FROM play_date";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            PlayDate playDate = mapRowToPlayDate(results);
            playDates.add(playDate);
        }
        return playDates;
    }

    public List<PlayDate> findDatesByPetId(int petId) {
        List<PlayDate> playDates = new ArrayList<>();
        String sql = "SELECT * FROM play_date WHERE pet_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
        while (results.next()) {
            PlayDate playDate = mapRowToPlayDate(results);
            playDates.add(playDate);
        }
        return playDates;
    }

    public PlayDate getPlayDateById(int playDateId){
        String sql = "SELECT * FROM pet WHERE date_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDateId);
        if (results.next()) {
            return mapRowToPlayDate(results);
        } else {
            return null;
        }
    }

    public int getUserIdFromPlayDateId(int playDateId) {

        return jdbcTemplate.queryForObject("SELECT user_id FROM pet JOIN play_date ON play_date.pet_id=pet.pet_id WHERE date_id = ?", int.class, playDateId);
    }

    public boolean createPlayDate(int petId, int locationId, String scheduledDate){
        String insertPlayDateSql= "INSERT INTO play_date (pet_id, location_id, scheduled_date) values (?,?,?)";
        return jdbcTemplate.update(insertPlayDateSql, petId, locationId, scheduledDate) ==1;
    }

    public boolean deleteDate(int playDateId){
        String sql = "DELETE FROM play_date WHERE date_id = ?;";
        jdbcTemplate.update(sql, playDateId);
        return true;
    }



    private PlayDate mapRowToPlayDate(SqlRowSet rs) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(rs.getInt("date_id"));
        playDate.setLocationId(rs.getInt("location_id"));
        playDate.setPetId(rs.getInt("pet_id"));
        playDate.setScheduledDate(rs.getString("scheduled_date"));


        return playDate;
    }
}
