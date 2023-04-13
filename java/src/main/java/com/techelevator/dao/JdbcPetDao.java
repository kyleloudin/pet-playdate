package com.techelevator.dao;

import com.techelevator.model.Pet;
import com.techelevator.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Copied directly from JdbcUserDao and changed a few words from User to Pet


@Component
public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByPetName(String petName) {
        if (petName == null) throw new IllegalArgumentException("Pet name cannot be null");

        int petId;
        try {
            petId = jdbcTemplate.queryForObject("SELECT pet_id FROM pet WHERE pet_name = ?", int.class, petName);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("Pet " + petName + " was not found.");
        }

        return petId;
    }



	@Override
	public Pet getPetByPetId(int petId) {
		String sql = "SELECT * FROM pet WHERE pet_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
		if (results.next()) {
			return mapRowToPet(results);
		} else {
			return null;
		}
	}

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            pets.add(pet);
        }

        return pets;
    }

    @Override
    public Pet findByPetName(String petName) {
        if (petName == null) throw new IllegalArgumentException("Pet name cannot be null");

        for (Pet pet : this.findAll()) {
            if (pet.getPetName().equalsIgnoreCase(petName)) {
                return pet;
            }
        }
        throw new UsernameNotFoundException("Pet " + petName + " was not found.");
    }


    public boolean createPet(int userId, String petName, LocalDate registeredDate, String animal, String breed, String gender, int rating, String size, String personality, int age, boolean spayedNeutered, String description, String favoriteToy, String petImgUrl) {
        String insertPetSql = "insert into pet (user_id, pet_name, registered_date, animal, breed, gender, rating, size, personality, age, spayed_neutered, description, favorite_toy, pet_img) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(insertPetSql, userId, petName, registeredDate, animal, breed, gender, rating, size, personality, age, spayedNeutered, description, favoriteToy, petImgUrl) == 1;
    }

    @Override
    public boolean deletePet(int petId) {
        String sql = "DELETE FROM review WHERE pet_id = ?;";
        jdbcTemplate.update(sql, petId);
        sql = "DELETE FROM pet WHERE pet_id = ?;";
        jdbcTemplate.update(sql, petId);
        return true;
    }

    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setPetId(rs.getInt("pet_id"));
        pet.setUserId(rs.getInt("user_id"));
        pet.setPetName(rs.getString("pet_name"));
        pet.setAnimal(rs.getString("animal"));
        pet.setBreed(rs.getString("breed"));
        pet.setGender(rs.getString("gender"));
        pet.setRating(rs.getInt("rating")); //todo - rating table?
        pet.setSize(rs.getString("size"));
        pet.setPersonality(rs.getString("personality"));
        pet.setAge(rs.getInt("age"));
        pet.setSpayedNeutered(rs.getBoolean("spayed_neutered"));
        pet.setRegisteredDate(rs.getDate("registered_date").toLocalDate());
        pet.setDescription(rs.getString("description"));
        pet.setFavoriteToy(rs.getString("favorite_toy"));
        pet.setPetImgUrl(rs.getString("pet_img"));

        return pet;
    }
}
