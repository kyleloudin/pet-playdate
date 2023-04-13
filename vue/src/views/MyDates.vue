<template>
  <div>
    <div
      class="list-all-dates" v-for="playDate in playDates" :key="playDate.date_id">
      <my-play-date :playDate="playDate"></my-play-date>
    </div>
  </div>
</template>

<script>
import PetService from "../services/PetService";
import MyPlayDate from "../components/MyPlayDate";

export default {
  name: 'MyDates',
  components: {
    MyPlayDate,
  },
  data() {
    return {
      playDates: [],
    };
  },
  created() {
    PetService.getMyPlayDates().then((response) => {
      this.playDates = response.data;
    });
  },

  methods: {
    //  listAllDates() {
    // PetService.getAllPlayDates().then((response) => {
    //   this.getAllPlayDates = response.data;
    // });
    // }
  },
};
</script>

<style>
</style>