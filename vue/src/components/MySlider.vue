<template>
  <div class="box">
    <div class="slider">
      <div class="front"></div>
      <div class="back"></div>
      <div class="left"></div>
      <div class="right"></div>
      <div class="four"></div>
      <div class="three"></div>
      <div class="two"></div>
      <div class="one"></div>
    </div>

    <span class="button"
      ><button @click="LeftSide">left</button>
      <button @click="RightSide">right</button></span
    >
  </div>
</template>

<script>
export default {
  name: "MySlider",
  data() {
    return {
      Change: 0,
    };
  },
  methods: {
    LeftSide() {
      this.Change += 45;
      document.querySelector(
        ".slider"
      ).style.transform = `perspective(800px) rotateX(270deg) rotate(${this.Change}deg) translateZ(-150px)`;
    },
    RightSide() {
      this.Change -= 45;
      document.querySelector(
        ".slider"
      ).style.transform = `perspective(800px) rotateX(270deg) rotate(${this.Change}deg) translateZ(-150px)`;
    },
  },
};
</script>

<style scoped>
.box {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.slider {
  width: 400px;
  height: 700px;
  /* background: transparent; */
  transform: perspective(800px) rotateX(270deg) rotate(0deg) translateZ(-150px);
  transform-style: preserve-3d;
  transition: 500ms;
  display: flex;
  align-items: center;
  justify-content: center;
}
.front,
.back,
.left,
.right,
.one,
.two,
.three,
.four {
  width: 50%;
  height: 200px;
  position: absolute;
  box-shadow: 0 0 5px #ccc, inset 0 0 100px #ddd;
  border-radius: 50px;
}
.button {
  display: flex;
  justify-content: center;
  margin-right: 180px;
}
.front {
  transform: rotateX(90deg) translate3d(0, 150px, 250px);
  background: url("../img/cat-five.jpg") 50% no-repeat;
  background-size: 150%;
}
.back {
  transform: rotateX(90deg) translate3d(0, 150px, -250px);
  background: url("../img/cat-six.jpg") 50% no-repeat;
  background-size: 150%;
}
.left {
  transform: rotateX(90deg) rotateY(45deg) translate3d(0px, 150px, -250px);
  background: url("../img/cat-seven.jpg") 50% no-repeat;
  background-size: 150%;
}
.right {
  transform: rotateX(90deg) rotateY(45deg) translate3d(0px, 150px, 250px);
  background: url("../img/cat-eight.jpg") 50% no-repeat;
  background-size: 150%;
}
.one {
  transform: rotateX(90deg) rotateY(90deg) translate3d(0px, 150px, 250px);
  background: url("../img/cat-eight.jpg") 50% no-repeat;
  background-size: 150%;
}
.two {
  transform: rotateX(90deg) rotateY(90deg) translate3d(0px, 150px, -250px);
  background: url("../img/cat-seven.jpg") 50% no-repeat;
  background-size: 150%;
}
.three {
  transform: rotateX(90deg) rotateY(135deg) translate3d(0px, 150px, -250px);
  background: url("../img/cat-seven.jpg") 50% no-repeat;
  background-size: 150%;
}
.four {
  transform: rotateX(90deg) rotateY(135deg) translate3d(0px, 150px, 250px);
  background: url("../img/cat-eight.jpg") 50% no-repeat;
  background-size: 150%;
}

button {
  text-transform: uppercase;
  text-shadow: 0 0 5px #666;
  transition: 100ms;
  position: relative;
  left: 100px;
  /* margin-left: 10px; */
}
button:active {
  text-shadow: 0 0 1px #666;
}
img {
  width: 100%;
  z-index: 1000;
  position: absolute;
  left: 160px;
  top: 24%;
  /* box-shadow: 0 0 5px #ddd; */
}
</style>