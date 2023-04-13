<template>
  <div class="pet">
    <search-pets />
    <my-pet />
   
  </div>
</template>

<script>
import SearchPets from "../components/SearchPets";
import MyPet from "../components/MyPet";

export default {
  name: "pet-lookup",
  components: {
    SearchPets,
    MyPet,
  
  },
  data() {
    return {
      pets: [],
      users: [],
    };
  },
};
</script>

<style>
.pet {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>