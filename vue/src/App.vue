<template>
  <div>
    <div id="app">
      <router-view />
      <sidebar />

      <mouse />
    </div>
    <div id="nav"></div>
  </div>
</template>
<script>
import Mouse from "../src/components/Mouse";
import Sidebar from "../src/components/Sidebar.vue";

export default {
  components: {
    Mouse,
    Sidebar,
  },
};
</script>


<style>
html {
  background-repeat: no-repeat;
  min-height: 100%;
}
@import "https://use.fontawesome.com/releases/v5.13.0/css/all.css";
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  max-width: 1fr;
  margin: auto;
}

#nav {
  padding: 30px;
  text-align: center;
}

#nav a {
  font-weight: bold;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

body {
  font-family: "Lato", sans-serif;
  background-image: linear-gradient(
    to bottom,
    teal,
    rgb(226, 144, 212),
    lavender
  );
}
</style>


