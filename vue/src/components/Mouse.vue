<template>
  <div id="app">
    <div id="nav"></div>
  </div>
</template>

<script>
export default {
  name: "mouse",
};

var dots = [],
  mouse = {
    x: 0,
    y: 0,
  };
var Dot = function () {
  this.x = 0;
  this.y = 0;
  this.node = (function () {
    var n = document.createElement("i");
    n.classList.add("trail");
    n.classList.add("fas");
    n.classList.add("fa-paw");
    n.classList.add("fa-1.5x");
    document.body.appendChild(n);
    return n;
  })();
};
Dot.prototype.draw = function () {
  this.node.style.left = this.x + "px";
  this.node.style.top = this.y + "px";
};
for (var i = 0; i < 8; i++) {
  var d = new Dot();
  dots.push(d);
}
function draw() {
  var x = mouse.x + 10,
    y = mouse.y;
  dots.forEach(function (dot, index, dots) {
    var nextDot = dots[index + 1] || dots[0];
    dot.x = x;
    dot.y = y;
    dot.draw();
    x += (nextDot.x - dot.x) * 0.9;
    y += (nextDot.y - dot.y) * 0.9;
  });
}
addEventListener("mousemove", function (event) {
  mouse.x = event.pageX;
  mouse.y = event.pageY;
});
function animate() {
  draw();
  requestAnimationFrame(animate);
}
animate();
</script>

<style>
@import "https://use.fontawesome.com/releases/v5.13.0/css/all.css";
.trail {
  position: absolute;
  height: 10px;
  width: 10px;
  border-radius: 6px;
  color: black;
}
</style>