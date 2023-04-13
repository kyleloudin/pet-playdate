<template>
  <div class="container">
    <h1>Contact Form</h1>
    <form action="/action_page.php" @submit.prevent="contact">
      <label for="fname">First Name</label>
      <input
        type="text"
        id="fname"
        name="firstname"
        placeholder="Your name.."
      />

      <label for="lname">Last Name</label>
      <input
        type="text"
        id="lname"
        name="lastname"
        placeholder="Your last name.."
      />

      <label for="location">Location</label>
      <select id="location" name="location">
        <option value="1">Cleveland</option>
        <option value="2">South Euclid</option>
        <option value="3">Kirtland</option>
        <option value="4">Twinsburg</option>
        <option value="5">Bay Village</option>
        <option value="6">Huron</option>
        <option value="7">Ravenna</option>
        <option value="8">Brecksville</option>
        <option value="9">Shaker Heights</option>
        <option value="10">Wellington</option>
        <option value="11">North Olmstead</option>
        <option value="0">**New Location**</option>
      </select>

      <label for="subject">Subject</label>
      <textarea
        id="subject"
        name="subject"
        placeholder="Write something.."
        style="height: 200px"
      ></textarea>

      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "contact-page",

  data() {
    return {};
  },
  methods: {
    contact() {
      this.$router.push({
        path: "/",
      });
    },
  },
};
</script>

<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}
* {
  box-sizing: border-box;
}

input[type="text"],
select,
textarea {
  text-align: center;
  justify-content: center;
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

button[type="submit"] {
  background-color: #0c39a3;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #a5c9ca;
  color: #2c3333;
}

form {
  justify-content: center;
  text-align: center;
  align-items: center;
  display: flex;
  flex-direction: column;
}

.container {
  text-align: center;
  justify-content: center;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  margin-left: 250px;
  margin-right: 250px;
}

h1 {
  text-align: center;
  justify-content: center;
}
</style>