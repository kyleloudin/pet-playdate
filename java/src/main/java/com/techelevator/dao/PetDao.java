package com.techelevator.dao;

import com.techelevator.model.Pet;
import com.techelevator.model.User;

import java.time.LocalDate;
import java.util.List;

public interface PetDao {

    List<Pet> findAll();

    Pet getPetByPetId(int petId);

    Pet findByPetName(String petName);

    int findIdByPetName(String petName);

    boolean createPet(int userId, String petName, LocalDate registeredDate, String animal, String breed, String gender, int rating, String size, String personality, int age, boolean spayedNeutered, String description, String favoriteToy, String petImgUrl);

    boolean deletePet(int petId);
}