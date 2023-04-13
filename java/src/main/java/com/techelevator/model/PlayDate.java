package com.techelevator.model;

import java.time.LocalDate;

public class PlayDate {


// Creating pets objects

   private int playDateId;
   private int petId;
   private int locationId;
   private String scheduledDate;


   public PlayDate() {}

   public PlayDate(int playDateId, int petId, int locationId, String scheduledDate) {
      this.playDateId = playDateId;
      this.petId = petId;
      this.locationId = locationId;
      this.scheduledDate = scheduledDate;

   }

   public int getPlayDateId() {
      return playDateId;
   }

   public void setPlayDateId(int playDateId) {
      this.playDateId = playDateId;
   }

   public int getPetId() {
      return petId;
   }

   public void setPetId(int petId) {
      this.petId = petId;
   }

   public int getLocationId() {
      return locationId;
   }

   public void setLocationId(int locationId) {
      this.locationId = locationId;
   }

   public String getScheduledDate() {
      return scheduledDate;
   }

   public void setScheduledDate(String scheduledDate) {
      this.scheduledDate = scheduledDate;
   }
}
