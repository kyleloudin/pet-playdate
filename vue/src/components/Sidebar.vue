<template>
  <div class="sidebar">
    <router-link v-bind:to="{ name: 'home' }"
      ><i class="fa fa-home" /> Home</router-link
    >&nbsp;
    <router-link
      v-bind:to="{ name: $store.state.token != '' ? 'logout' : 'login' }"
      ><i class="fa fa-user" />{{
        $store.state.token != "" ? " Logout" : " Login"
      }}</router-link
    >&nbsp;
    <router-link
      v-bind:to="{
        name: $store.state.token != '' ? 'register-pet' : 'register',
      }"
      ><i class="fa fa-address-card" />{{
        $store.state.token != "" ? " Register New Pet" : " Register"
      }}</router-link
    >&nbsp;
    <router-link v-bind:to="{ name: 'profile' }"
      ><i class="fa fa-paw" /> Profile</router-link
    >&nbsp;
    <router-link v-bind:to="{ name: 'contact' }"
      ><i class="fa fa-comments" /> Contact</router-link
    >&nbsp;
    <router-link v-bind:to="{ name: 'FindPets' }"
      ><i class="fa fa-search" /> Find Pets</router-link
    >&nbsp;
    <router-link v-bind:to="{ name: 'DateForm' }"
      ><i class="fa fa-calendar" /> Schedule A Playdate</router-link
    >
    &nbsp;
    <router-link v-bind:to="{ name: 'list-play-dates' }"
      ><i class="fa fa-user" /> List of Playdates </router-link
    >
  </div>
</template>

<script>
export default {
  name: "sidebar",
};
</script>

<style>
.sidebar {
  float: left;
  height: 100%;
  width: 160px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  overflow-x: hidden;
  padding-top: 16px;
}
.sidebar a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 20px;
  color: black;
  display: block;
  text-align: left;
}
.sidebar a:hover {
  color: #f1f1f1;
}
@media screen and (max-height: 450px) {
  .sidebar {
    padding-top: 15px;
  }
  .sidebar a {
    font-size: 18px;
  }
}
</style>