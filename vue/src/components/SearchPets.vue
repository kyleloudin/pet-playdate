<template>
  <div class="search-pet">
    <link href='https://fonts.googleapis.com/css?family=Luckiest Guy' rel='stylesheet'>
    <h1 >Search Fur a Friend</h1>
    <div class="container">
      <form id="search-pets" v-on:submit.prevent="findPets">
        <input type="search" v-model="search" />

        <button  type="submit">Submit</button>
      </form>

      <div class="list-pets" v-for="pet in pets" :key="pet.pet_id">
        <!-- <img src="../img/all-dogs.jpg" alt="Dog"> -->
        <my-pet level="0" :pet="pet"></my-pet>
      </div>
    </div>

        <h4>There are many wonderful pet friends to chose from.</h4>

        <!--     "request_id": 1,
    "employee_id": 1,
    "number_of_tickets": 2,
    "date_requested": "2022-04-01T12:00:00Z",
    "game_date": "2022-04-15T19:10:00Z",
    "entry_time": "2022-04-15T18:30:00Z",
    "isApproved": "yes" -->
    
    <h4>Type a name or letter to see!</h4>
  </div>
</template>

<script>
import MyPet from "../components/MyPet";
import PetService from "../services/PetService";

export default {
  name: "SearchPets",
  components: {
    MyPet,
  },

  data() {
    return {
      search: "",
      pets: [],
    };
  },
  methods: {
    findPets() {
      PetService.getPets(this.search).then((response) => {
        this.pets = response.data;
      });
    },
  },
  computed: {
    //     filteredPets() {
    //         return this.pets.filter(pet =>
    //         pet.petName.toLowerCase().includes(this.search.toLowerCase())
    //         );
    //      }
  },
};
</script>

<style>
h1 {
  text-align: center;
  font-family: "Luckiest Guy";
  font-size: xx-large;
  }

h4 {
  text-align: center;
  justify-content: center;
}  

/* .search-pet .container .search-pets{
  max-width: 400px;
} */

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
  width: 40%;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.container > #search-pets {
  display: flex;
  float: center;
  min-width: 300px;
} 

.container {
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  padding: 20px 30px;
  margin: 1px;
  color: black;
}

button[type="submit"] {
  background-color: #0c39a3;
  color: white;
  border: none;
  cursor: pointer;
  width: 50%;
  opacity: 0.9;
}

button[type="submit"]:hover {
  color: #2c3333;
}


/* Rounded border */
 hr.rounded { 
  border-top: 8px solid #bbb;
  border-radius: 5px;
}
</style>
