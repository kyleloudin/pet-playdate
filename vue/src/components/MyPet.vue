<template>
  <div class="pet-card">
    <!-- <article class="container"> -->
    <div class="top-bar">
      <div class="image">{{ pet.imageUrl }}</div>
      <div class="middle-section">
        <h2 class="pet-name">{{ pet.petName }}</h2>
        <img id="pet-img" :src="pet.petImgUrl" />
        <h2 class="description">{{ pet.animal }}</h2>
        <h2 class="breed">{{ pet.breed }}</h2>
        <h3 class="owner">Owner: {{ getUserName(pet.userId) }}</h3>
      </div>
      <div class="right-section">
        <h2 class="gender">Gender: {{ pet.gender }}</h2>
        <h2 class="age">Age: {{ pet.age }}</h2>
      </div>
    </div>
    <div class="bottom-bar">
      <h2 class="description">Description: <br />{{ pet.description }}</h2>
      <div class="right-section">
        <h2 class="personality">Personality: <br />{{ pet.personality }}</h2>
      </div>
      <div>
        <p>
          <button v-show="level == '1'" v-on:click="deleteMe(pet.id)">
            Delete Pet
          </button>
          <router-link
            v-show="level == '0'"
            class="button"
            v-bind:to="{ name: 'DateForm' }"
            style="font-size: 32px;"
            ><i class="fa fa-paw" />Select Playdate</router-link
          >
        </p>
      </div>
    </div>
    <!-- </article> -->
  </div>
</template>

<script>
import PetService from "../services/PetService";
export default {
  
  name: "MyPet",
  props: ["pet", "level"],
  data() {
    return {
      users: [],
      name: "",
    }
  },
   created() {
    PetService.getAllUsers().then((response) => {
      this.users = response.data;
    });
   },
  methods: {
    deleteMe(petID) {
      PetService.deletePet(petID).then(() => {
        this.$router.go();
      });
    },
    getUserName(userId){
      this.users.forEach(user => {
        if(user.id == userId) {
          this.name = user.username;
        }
      });
      return this.name;
    },
  },
};
</script>
<style>
/* body {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  grid-template-areas:
    "content"
    "description";
  background: linear-gradient(to bottom, teal, rgb(226, 144, 212), lavender);
} */
.pet-card {
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  border: 5px solid rgb(69, 23, 196);
  padding: 1px;
  margin: 5px;
  align-items: center;
  justify-content: center;
}

#pet-img{
  display: flex;
  margin-left: auto;
  margin-right: auto;
  max-width: 80%;
}

#pets-parent {
  margin-left: 80px;
}
.pet-name {
  color: rgb(69, 23, 196);
  grid-area: pet-name;
  font-size: 30px;
}
.owner,
.gender,
.age {
  color: black;
  font-size: 15px;
  grid-area: content;
}

.description {
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  color: rgb(20, 19, 19);
  grid-area: description;
  font-size: 20px;
}

.personality {
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  color: rgb(20, 19, 19);
  grid-area: personality;
  font-size: 20px;
}

.breed {
  color: rgb(17, 17, 16);
}

.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  /* gap: 1px;   */
}
</style>