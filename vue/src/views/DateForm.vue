<template>
  <div>
    <form action="/action_page.php" @submit.prevent="registerPlayDate()">
      <label for="myPet">Select your pet!</label>
      <select id="myPet" name="myPet" v-model="playDate1.petId">
        <option
          v-for="(myPet, key) in myPets"
          :key="key"
          v-bind:value="myPet.id"
        >
          {{ myPet.petName }}
        </option>
      </select>

      <label for="otherPet">Select your pet's friend!</label>
      <select id="otherPet" name="otherPet" v-model="playDate2.petId">
        <option
          v-for="(allPet, key) in allPets"
          :key="key"
          v-bind:value="allPet.id"
        >
          {{ allPet.petName }}
        </option>
      </select>

      <label for="location">Location</label>
      <select id="location" name="location" v-model="playDate1.locationId">
        <option
          v-for="(location, key) in locations"
          :key="key"
          v-bind:value="location.location_id"
        >
          {{ location.parkName }}
        </option>
      </select>

      <datepicker
        id="textagain"
        placeholder="Schedule a Date"
        v-model="playDate1.scheduledDate"
      ></datepicker>

      <label for="subject">Message</label>
      <textarea
        id="subject"
        name="subject"
        placeholder="Send a message to your pet friend with a time you'd like to meet.."
        style="height: 200px"
      ></textarea>

      <button type="submit">Submit</button>
    </form>
    <div class="map">
      <g-map id="map" />
    </div>
  </div>
</template>

<script>
import PetService from "../services/PetService";
import Datepicker from "vuejs-datepicker";
import GMap from "../components/GMap";

export default {
  name: "DateForm",
  components: {
    Datepicker,
    GMap,
  },
  data() {
    return {
      locations: [],
      myPets: [],
      allPets: [],
      newPets: [],
      playDate1: {
        petId: "",
        scheduledDate: "",
        locationId: "",
      },
      playDate2: {
        petId: "",
        scheduledDate: "",
        locationId: "",
      },
    };
  },
  methods: {
    registerPlayDate() {
      this.playDate2.scheduledDate = this.playDate1.scheduledDate;
      this.playDate2.locationId = this.playDate1.locationId;
      PetService.registerPlayDate(this.playDate1)
        .then((response) => {
          if (response.status == 201) {
            this.$router.push({
              query: { registration: "success" },
            });
          }
        })
        .catch((error) => {
          const response = error.response;
          this.registrationErrors = true;
          if (response.status === 400) {
            this.registrationErrorMsg = "Bad Request: Validation Errors";
          }
        });

      PetService.registerPlayDate(this.playDate2)
        .then((response) => {
          if (response.status == 201) {
            this.$router.push({
              query: { registration: "success" },
            });
          }
          alert("Schedluing successful!");
          this.$router.push({ name: "MyDates" });
        })
        .catch((error) => {
          const response = error.response;
          this.registrationErrors = true;
          if (response.status === 400) {
            this.registrationErrorMsg = "Bad Request: Validation Errors";
          }
        });
    },
  },
  created() {
    PetService.getMe().then((response) => {
      this.user = response.data;
    });
    PetService.getMyPets().then((response) => {
      this.myPets = response.data;
    });
    PetService.getAllPetsBarMine().then((response) => {
      this.allPets = response.data;
    });
    PetService.getLocations().then((response) => {
      this.locations = response.data;
    });
  },
};
</script>

<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}
* {
  box-sizing: border-box;
}

input[type="text"],
select,
textarea {
  width: 50%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  font-size: 16px;
  resize: vertical;
}

#textagain {
  width: 100%;
}

button {
  background-color: #0c39a3;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #a5c9ca;
  color: #2c3333;
}

#map {
  margin-left: auto;
  margin-right: auto;
}

.container {
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  margin-left: 250px;
  margin-right: 250px;
}

.map {
  text-align: center;
  justify-content: center;
}
</style>
