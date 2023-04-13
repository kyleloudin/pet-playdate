<template>
  <div class="event-card">
    <h1>Pet Playing: {{ getPetName(playDate.petId) }}</h1>
    <h2>Date: {{ playDate.scheduledDate }}</h2>
    <h2>Location: {{ getLocationName(playDate.locationId) }}</h2>
    <button id="delete" v-on:click="removePlayDate(playDate.playDateId)">Delete Date</button>
  </div>
</template>

<script>
import PetService from "../services/PetService";

export default {
  name: "MyPlayDate",

  props: ["playDate"],
  data() {
    return {
      allPets: [],
      allLocations: [],
      name: "",
      locationName: "",
    };
  },
  created() {
    PetService.getAllPets().then((response) => {
      this.allPets = response.data;
    });

    PetService.getLocations().then((response) => {
      this.allLocations = response.data;
    });
  },
  methods: {
    removePlayDate(playDateId) {
      PetService.deletePlayDate(playDateId).then(() => {
        this.$router.go();
      });
    },
    getPetName(petId){
      this.allPets.forEach(pet => {
        if(pet.id == petId) {
          this.name = pet.petName;
        }
      });
      return this.name;
    },
    getLocationName(locationId){
      this.allLocations.forEach(location => {
        if(location.location_id == locationId) {
          this.locationName = location.parkName;
        }
      });
      return this.locationName;
    }
  },
};
</script>

<style>
h2 {
  text-align: center;
}
#delete {
  display: flex;
  align-content: center;
  margin-left: auto;
  margin-right: auto;
}
</style>