<template>
  <div>
    <navigation />
    <div class="background-image-profile">
      <div class="image-profile-text">
        <div class="welcome">
          <img id="user-img" v-bind:src="user.userImgUrl" style="height: 100%" />

          <h1>WELCOME, {{ user.username }}!</h1>
        </div>
        <my-slider />

        <div id="profile">
          <div id="pets-parent">
            <h2>MY PET FAMILY!</h2>
            <div class="list-pets" v-for="pet in pets" :key="pet.pet_id">
              <!-- <img src="../img/all-dogs.jpg" alt="Dog"> -->
              <my-pet :pet="pet" level="1"></my-pet>
            </div>
            <div id="forms">
              <RegisterPet v-show="addPet" v-on:toggleForm="toggleForm" />
            </div>
            <div id="pet-card">
              <ProfilePets
                v-show="viewPets"
                v-on:toggleForm="toggleForm"
                v-bind:username="username"
                v-on:userPets="assignPetArray"
              />
            </div>
          </div>

          <div id="playdates-parent">
            <div
              class="playdate-buttons"
              v-for="playDate in playDates"
              :key="playDate.date_id"
            >
              <my-play-date :playDate="playDate"></my-play-date>
              <button
                v-show="showRequests"
                id="requests-button"
                class="borderBlink"
              >
                <router-link :to="{ name: 'playdateRequests' }">
                  You Have New Playdate Requests! <br /><span class="smaller">
                    view now
                  </span></router-link
                >
              </button>
              <check-for-requests v-on:hasRequests="hasRequests" />
            </div>
            <div id="playdates">
              <CreatePlaydate
                v-if="addPlaydate"
                v-on:toggleThisForm="showProfile"
                v-bind:userPets="arrayOfPets"
              />
              <div v-if="viewPlaydates">
                <div
                  class="card"
                  v-for="playdate in userPlaydates"
                  :key="playdate.playdateId"
                >
                  <div class="playdate">
                    <div id="user">{{ playdate.username }}</div>
                    <div id="time">
                      {{ playdate.playdateDate }} at {{ playdate.playdateTime }}
                    </div>
                    <div id="space"></div>
                    <div id="address">
                      {{ playdate.address }}<br />
                      {{ playdate.city }}, {{ playdate.region }}
                      {{ playdate.zipcode }}
                    </div>
                  </div>
                  <div id="buttons">
                    <router-link
                      :to="{
                        name: 'playdateDetail',
                        params: { id: playdate.playdateId },
                      }"
                      tag="button"
                      class="playdates-button"
                    >
                      Details/Chat </router-link
                    ><br />
                    <button
                      v-on:click.prevent="
                        (editPlaydate = true), (viewPlaydates = false);
                        selectedPlaydate = playdate;
                      "
                      class="playdates-button"
                    >
                      Edit</button
                    ><br />
                    <button
                      v-on:click.prevent="
                        addPetToPlaydate = true;
                        viewPlaydates = false;
                        selectedPlaydate = playdate;
                      "
                      class="playdates-button"
                    >
                      Add pet</button
                    ><br />
                  </div>
                </div>
                <div>
                  <div>
                <br />
                <br />
                <br />
                <br />
                    <router-Link
                      id="registerPet"
                      v-bind:to="{ name: 'MyDates' }"
                    >
                     View My Playdates!
                    </router-Link>
                  </div>
                </div>
                <br />
                <br />
                <br />
                <br />
                <div>
                  <router-Link
                    id="registerPet"
                    v-bind:to="{ name: 'DateForm' }"
                  >
                    Schedule a Playdate!
                  </router-Link>
                </div>
                <br />
                <br />
                <br />
                <br />
                <div>
                  <router-Link
                    id="registerPet"
                    v-bind:to="{ name: 'register-pet' }"
                  >
                    Register a New Pet!
                  </router-Link>
                </div>
                 <br />
                <br />
                <br />
                <br />
                <div>
                  <router-Link
                    id="registerPet"
                    v-bind:to="{ name: 'MyProfile' }"
                  >
                    Edit My Profile!
                  </router-Link>
                </div>
              </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RegisterPet from "../views/RegisterPet";
import ProfilePets from "../components/MyPet";
import MySlider from "../components/MySlider";
// import DateForm from "../views/DateForm"

import PetService from "../services/PetService";
import MyPet from "../components/MyPet.vue";
import MyPlayDate from "../components/MyPlayDate.vue";
export default {
  data() {
    return {
      selectedPlaydate: {},
      user: [],
      addPet: false,
      viewPets: true,
      viewPlaydates: true,
      addPlaydate: false,
      editPlaydate: false,
      addPetToPlaydate: false,
      userPlaydates: [],
      pets: [],
      playDates: [],
      showRequests: false,
    };
  },
  methods: {
    hasRequests(value) {
      if (value) {
        this.showRequests = true;
      } else {
        this.showRequests = false;
      }
    },
    resetPage() {
      this.addPet = false;
      this.viewPets = true;
      this.viewPlaydates = true;
      this.addPlaydate = false;
      this.editPlaydate = false;
      this.addPetToPlaydate = false;
      this.showPlaydates();
    },
    showProfile() {
      this.addPet = false;
      this.addPlaydate = false;
      this.editPlaydate = false;
      this.addPetToPlaydate = false;
      this.viewPets = true;
      this.viewPlaydates = true;
      this.showPlaydates();
    },
    assignPetArray(petArray) {
      this.arrayOfPets = petArray;
    },
    toggleForm(value) {
      this.addPet = value;
      this.addPlaydate = false;
      this.viewPets = true;
    },
    
  },
  components: {
    RegisterPet,
    ProfilePets,
    MySlider,

    MyPet,
    MyPlayDate,
  },
  created() {
    PetService.getMe().then((response) => {
      this.user = response.data;
    });
    PetService.getMyPets().then((response) => {
      this.pets = response.data;
    });
   
  },
};
</script>

<style>
#user-img{
  max-width: 100px;
}

.welcome {
  display: flex;
  margin-left: 10px;
  align-items: center;
  justify-content: center;
}

.welcome :nth-child(2) {
  font-size: 40px;
}

#forms {
  display: block;
}

#pet-card {
  width: 100%;
  height: auto;
  margin: auto;
  text-align: center;
  padding: 10px 10px 10px 10px;
  border-radius: 12px;
  color: black;
  font-size: 0.5vw;
}
#profile {
  display: grid;
  grid-template-columns: 12% 30% 16% 30% 12%;
  grid-template-areas: ". pet . playdate . pet-card";
}
#pets-parent {
  grid-area: pet;
}
#playdates-parent {
  grid-area: playdate;
}

#registerPet {
  float: center;
  padding: 2%;
  border-radius: 12px;
  background-image: linear-gradient(
    to bottom right,
    rgb(71, 83, 252),
    rgb(153, 252, 194)
  );
  font-size: 2.5vw;
  color: black;
  transition-duration: 0.2s;
  width: 80%;
}

#addPlaydate {
  float: center;
  padding: 2%;
  border-radius: 12px;
  background-image: linear-gradient(
    to bottom right,
    rgb(71, 83, 252),
    rgb(153, 252, 194)
  );
  font-size: 1.2vw;
  color: black;
  transition-duration: 0.2s;
  width: 50%;
}
</style>