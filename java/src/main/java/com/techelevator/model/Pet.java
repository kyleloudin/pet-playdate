package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pet {


// Creating pets objects

   private int petId;
   private int userId;
   private String petName;
   private String animal;
   private String breed;
   private String gender;
   private int rating = 0;
   private String size;
   private String personality;
   private int age;
   private boolean spayedNeutered;
   private LocalDate registeredDate = LocalDate.now();
   private String description;
   private String favoriteToy;
   private String petImgUrl;



   public Pet() {}

   public Pet(int petId, int userId, String petName, LocalDate registeredDate, String animal, String breed, String gender, int rating, String size, String personality, int age, boolean spayedNeutered, String description, String favoriteToy, String petImgUrl) {
      this.petId = petId;
      this.userId = userId;
      this.petName = petName;
      this.animal = animal;
      this.breed = breed;
      this.gender = gender;
      this.rating = rating;
      this.size = size;
      this.personality = personality;
      this.age = age;
      this.spayedNeutered = spayedNeutered;
      this.registeredDate = registeredDate;
      this.description = description;
      this.favoriteToy = favoriteToy;
      this.petImgUrl = petImgUrl;

   }

   public int getId() {
      return petId;
   }

   public void setPetId(int petId) {
      this.petId = petId;
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public void setPetName(String petName) {
      this.petName = petName;
   }

   public String getPetName() {
      return petName;
   }

   public String getAnimal() {
      return animal;
   }

   public void setAnimal(String animal) {
      this.animal = animal;
   }

   public String getBreed() {
      return breed;
   }

   public void setBreed(String breed) {
      this.breed = breed;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public int getRating() {
      return rating;
   }

   public void setRating(int rating) {
      this.rating = rating;
   }

   public String getSize() {
      return size;
   }

   public void setSize(String size) {
      this.size = size;
   }

   public String getPersonality() {
      return personality;
   }

   public void setPersonality(String personality) {
      this.personality = personality;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public boolean isSpayedNeutered() {
      return spayedNeutered;
   }

   public void setSpayedNeutered(boolean spayedNeutered) {
      this.spayedNeutered = spayedNeutered;
   }

   public LocalDate getRegisteredDate() {
      return registeredDate;
   }

   public void setRegisteredDate(LocalDate registeredDate) {
      this.registeredDate = registeredDate;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getFavoriteToy() {
      return favoriteToy;
   }

   public void setFavoriteToy(String favoriteToy) {
      this.favoriteToy = favoriteToy;
   }

   public String getPetImgUrl() {
      return petImgUrl;
   }

   public void setPetImgUrl(String petImgUrl) {
      this.petImgUrl = petImgUrl;
   }
}
