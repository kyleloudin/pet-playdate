<template>
  <div>
    <div class="pet">
      <search-pets />
    </div>
    
  </div>
</template>

<script>


import SearchPets from "../components/SearchPets";
// import MyPet from '../components/MyPet'

export default {
  name: "FindPets",
  components: {
    SearchPets,
   

    // MyPet
  },
  data() {
    return {
      pets: [],
    };
  },
};
</script>

<style>
.pet {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

}
</style>