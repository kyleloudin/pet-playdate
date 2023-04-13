<template>
  <div>
    <navigation />
    <div class="signup">
      <h1>PlayDate Details</h1>
      <div class="event-pet-details-card">
        <h2>Location</h2>
        {{ playDate.location_id }}, {{ playDate.petId1 }},
        {{ playDate.petId2 }} Date: {{ playDate.scheduledDate }} <br />

        <br />
      </div>
    </div>
  </div>
</template>

<script>
import PetService from "../services/PetService";

export default {
  name: "schedule-dates",
  data() {
    return {
      playDates: [],
      playDate1: {
        pet_id: "",
        scheduled_date: "",
        location_id: "",
      },
      playDate2: {
        pet_id: "",
        scheduled_date: "",
        location_id: "",
      },
    };
  },
  methods: {
    registerPlayDate() {
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
};
</script>

<style>
body {
  text-align: center;
}
.accepted {
  color: blue;
}
.denied {
  color: red;
}
.event-pet-details-card {
  border: 1px solid black;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  width: 40%;
  height: auto;
  margin: auto;
  text-align: center;
  font-family: arial;
  float: center;
  background-color: #b9b6b6;
  padding-left: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  border-radius: 12px;
}
.pet-submit-button {
  border-radius: 4px;
  padding: 1%;
  border: 2px solid rgb(206, 206, 206);
  background-color: rgb(207, 207, 207);
  color: black;
  margin: 6px 10px 10px 10px;
  width: 22%;
  display: flex;
  flex-wrap: wrap;
}
.pet-submit-button:hover {
  box-shadow: 0 12px 16px 0 rgb(105, 105, 105), 0 17px 50px 0 rgb(63, 63, 63);
}
</style>