<template>
  <div id="register-pet" class="text-center">
    <link
      href="https://fonts.googleapis.com/css?family=Luckiest Guy"
      rel="stylesheet"
    />

    <div id="grid">
      <div class="cartoon-column">
        <img src="../img/lamb.png" style="width: 25%" />
        <img src="../img/dog.png" style="width: 25%" />
        <img src="../img/cow.png" style="width: 35%" />
        <img src="../img/squirrel.png" style="width: 25%" />
        <img src="../img/duck.png" style="width: 25%" />
      </div>
    </div>

    <form class="form-register-pet" @submit.prevent="registerPet">
      <h1 class="h3 mb-3 font-weight-normal">Create New Pet</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <label for="pet-name" class="sr-only">Pet Name</label>
      <input
        type="text"
        id="pet-name"
        class="form-control"
        placeholder="Pet's Name"
        v-model="pet.petName"
        required
        autofocus
      />

      <label for="pet-animal" class="sr-only">Animal</label>
      <input
        type="text"
        id="animal"
        class="form-control"
        placeholder="Type of Animal"
        v-model="pet.animal"
        required
        autofocus
      />

      <label for="pet-breed" class="sr-only">Breed</label>
      <input
        type="text"
        id="breed"
        class="form-control"
        placeholder="Breed (optional)"
        v-model="pet.breed"
        autofocus
      />
      <div>
        <h3>Gender</h3>
        <div class="radio">
          <label for="gender">M</label>
          <input
            type="radio"
            id="genderA"
            class="form-control"
            value="M"
            v-model="pet.gender"
            checked
          />
        </div>
        <div class="radio">
          <label for="gender">F</label>
          <input
            type="radio"
            id="genderB"
            class="form-control"
            value="F"
            v-model="pet.gender"
            required
          />
        </div>
      </div>

      <!-- todo dropdown small med large -->
      <label for="pet-size" class="sr-only">Size</label>
      <input
        type="text"
        id="size"
        class="form-control"
        placeholder="Size"
        v-model="pet.size"
        required
        autofocus
      />

      <!-- Tickbox: playful, relaxed, sociable, curious, loner, high energy, territorial -->
      <label for="pet-personality" class="sr-only">Personality</label>
      <input
        type="text"
        id="personality"
        class="form-control"
        placeholder="Personality"
        v-model="pet.personality"
        required
        autofocus
      />

      <label for="pet-age" class="sr-only">Age</label>
      <input
        type="number"
        id="age"
        class="form-control"
        placeholder="Age"
        v-model="pet.age"
        autofocus
        min="0"
        required
      />

      <div>
        <h3>Spayed/Neutered</h3>
        <div class="radio">
          <label for="pet-spayed-neutered">Yes </label>
          <input
            type="radio"
            id="infertileA"
            class="form-control"
            value="true"
            placeholder="Are they spayed/neutered?"
            v-model="pet.spayedNeutered"
            required
          />

        </div>
        <div class="radio">
          <label for="pet-spayed-neutered">No</label>
          <input
            type="radio"
            id="infertileB"
            class="form-control"
            value="false"
            placeholder="Are they spayed/neutered?"
            v-model="pet.spayedNeutered"
            required
            
          />
        </div>
      </div>

      <label for="pet-description" class="sr-only">Description</label>
      <textarea
        rows="5"
        type="text"
        id="description"
        class="form-control"
        placeholder="Description"
        v-model="pet.description"
        required
        autofocus
      />

      <label for="pet-toy" class="sr-only">Favorite Toy</label>
      <input
        type="text"
        id="favorite-toy"
        class="form-control"
        placeholder="Favorite Toy (optional)"
        v-model="pet.favoriteToy"
        autofocus
      />

      <label for="imageUrl" class="sr-only">User image URL</label>
      <input
        type="text"
        id="petImgUrl"
        class="form-control"
        placeholder="Pet image URL"
        v-model="pet.petImgUrl"
        autofocus
        required
        />


      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Add Pet
      </button>
      <br/>
      <br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>


    </form>

    <div id="grid">
      <div class="other-cartoon-column">
        <img src="../img/lamb.png" style="width: 25%" />
        <img src="../img/dog.png" style="width: 25%" />
        <img src="../img/cow.png" style="width: 35%" />
        <img src="../img/squirrel.png" style="width: 25%" />
        <img src="../img/duck.png" style="width: 25%" />
      </div>
    </div>
  </div>
</template>

<script>
import petService from "../services/PetService";

export default {
  name: "register-pet",
  data() {
    return {
      pet: {
        petName: "",
        animal: "",
        breed: "",
        gender: "",
        size: "",
        personality: "",
        age: 0,
        spayedNeutered: "",
        description: "",
        favoriteToy: "",
        petImgUrl: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this pet.",
    };
  },
  methods: {
    registerPet() {
      petService
        .registerPet(this.pet)
        .then((response) => {
          if (response.status == 201) {
            this.$router.push({
              path: "/",
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
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this pet.";
    },
  },
};
</script>

<style>
#grid {
  /* background-color: white; */
  display: -webkit-flex;
  display: flex;
  flex-direction: column;
  width: 2fr 2fr 2fr;
  height: 400px;
  
}

.cartoon-column {
  display: flex;
  flex-direction: column;
    
}

.cartoon-column :nth-child(1) {
    margin: 115px;
  margin-right: auto;
}

.cartoon-column :nth-child(2) {
    margin: 115px;
  margin-right: auto;
}

.cartoon-column :nth-child(3) {
    margin: 115px;
  margin-right: auto;
}

.cartoon-column :nth-child(4) {
    margin: 115px;
  margin-right: auto;
}

.cartoon-column :nth-child(5) {
  margin: 115px;
  margin-right: auto;
}

.other-cartoon-column {
  display: flex;
  flex-direction: column;
 
}
.other-cartoon-column :nth-child(1) {
    margin: 115px;
  margin-left: auto;
}

.other-cartoon-column :nth-child(2) {
    margin: 115px;
  margin-left: auto;
}

.other-cartoon-column :nth-child(3) {
    margin: 115px;
  margin-left: auto;
}

.other-cartoon-column :nth-child(4) {
  margin: 115px;
  margin-left: auto;
}

.other-cartoon-column :nth-child(5) {
  margin: 115px;
  margin-left: auto;
}

h1 {
  text-align: center;
  display: flex;
}


#register-pet {
  
  display: flex;
  flex-direction: row;
  flex-basis: 2fr;
  max-width: 80%;
  margin: auto;
  font-family: "Montserrat", sans-serif;
  /* color: #2c3333; */
}

.form-register-pet {
  margin: 15px 0;
  font-size: 1em;
  display: grid;
}

label.sr-only {
  font-size: 1.3em;
}

.radio {
  display: flex;
  align-items: center;
}

.form-control {
  max-width: 325px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid black;
  line-height: 25px;
  display: flex;
  margin: 15px 0;
  align-items: center;
  flex-direction: column;
}

button[type="submit"] {
  background-color: #4947ce;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button[type="submit"]:hover {
  background-color: #a5c9ca;
  color: #2c3333;
}

@media (min-width: 670px) {
  #register-pet {
    text-align: center;
  }

  .form-control {
    margin: 15px 0;
  }

  .form-register-pet {
    display: flex;
    text-align: left;
  }

  button[type="submit"] {
    max-width: 540px;
  }
}
</style>
