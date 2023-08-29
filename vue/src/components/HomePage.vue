<template>
  <div class="home">
    <link href='https://fonts.googleapis.com/css?family=Luckiest Guy' rel='stylesheet'>
    <header class='header'>
      <img src="../img/trial-gif2.gif" style="width:10%">
      <h1> <i class="fa fa-paw fa-3x"></i>Pet Playdate<i class="fa fa-paw fa-3x"></i></h1> 
      <img src="../img/trial-gif2.gif" style="width:10%" >
    </header>
    
   
    <p class='tagline'><strong> Our Friends Are Cuter Than Yours! </strong>
    <h4><em>Pets are awesome! They bring us so much joy; let's find them friends to play with!</em></h4> 
    <div id="button">
    <router-link :to="{ name: 'DateForm'}"><button>Schedule a Play Date Here</button></router-link>
    </div>
    <div class="row">
      <div class="column">
        <img src="../img/Kyles2.jpg" style="width: 100%" />
        <img src="../img/Arri.jpg" style="width: 100%" />
        <img src="../img/Tori.png" style="width: 100%" />
        <img src="../img/brett.jpg" style="width: 100%" />
        <img src="../img/Arri2.jpg" style="width: 100%" />
      </div>
      <div class="column">
        <img src="../img/Frank.jpg" style="width: 100%" />
        <img src="../img/Max.jpg" style="width: 100%" />
        <img src="../img/DonDon.jpg" style="width: 100%" />
        <img src="../img/Tori2.jpg" style="width: 100%" />
        <img src="../img/kyle.jpg" style="width: 100%" />
      </div>

      <div class="column">
        <img src="../img/Kaitlin.jpg" style="width: 100%" />
        <img src="../img/Lauren.jpg" style="width: 100%" />
        <img src="../img/brett2.jpg" style="width: 100%" />
        <img src="../img/dog-in-water.jpg" style="width: 100%" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home-page",
};
</script>

<style>
.header {
  display: flex;
  justify-content: space-evenly;
  flex-direction: row;
  align-items: center;
  margin-left: 200px;
  margin-right: 200px;
}

header img {
  height: 100%;
}

h1 {
  display: flex;
  align-items: center;
  /* grid-row-start: auto; */
  padding: 0.3em;
  padding-top: 60px;
  text-align: center;
  text-shadow: 2px 2px 5px white;
  color: rgb(10, 9, 9);
  font-size: 3.3vw;
  font-family: 'Luckiest Guy';
  flex-basis: 1fr; 
}

h4 {
  display: flex;
  text-align: center;
  align-items: center;
  flex-basis: 1fr;
  font-size: 2vw;
  padding-left: 120px;
  padding-right: 120px;
}

.tagline {
  font-size: 2.5vw;
}

img {
  border-radius: 30px;
}

p {
  text-align: center;
  font-size: 40px;
  text-decoration-color: black;
}

.home {
  border-radius: 3px;
}

.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  padding: 0 4px;
  justify-content: center;
  margin-left: 80px;
  margin-right: 80px;
}

#button {
  display: flex;
  justify-content: center;
  text-align: center;
  
}
button {
  background-color: #0c39a3;
  color: white;
  padding: 10px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #a5c9ca;
  color: #2c3333;
}

.column {
  display: flex;
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
  max-width: 25%;
  padding: 0 4px;
  flex-wrap: wrap;
  justify-content: space-around;
}

.column img {
  margin-top: 8px;
  vertical-align: middle;
  width: 100%;
}

/* Responsive layout - makes a two column-layout instead of four columns */
@media screen and (max-width: 800px) {
  .column {
    -ms-flex: 50%;
    flex: 50%;
    max-width: 50%;
  }
}

/* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .column {
    -ms-flex: 100%;
    flex: 100%;
    max-width: 100%;
  }
}
</style>