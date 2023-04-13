<template>
  <div id="register" class="text-center">
     <link href='https://fonts.googleapis.com/css?family=Luckiest Guy' rel='stylesheet'>
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="username"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />

      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />

      <label for="confirmPassword" class="sr-only">Confirm Password</label>
      <input
        type="Password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />

      <label for="emailAddress" class="sr-only">Email Address</label>
      <input
        type="emailAddress"
        id="emailAddress"
        class="form-control"
        placeholder="Enter Email"
        v-model="user.emailAddress"
        required
      />

      <label for="location"><h3>Location</h3></label>
      <select id="location" name="location" v-model="user.userLocation">
        <option value="1" required>Cleveland</option>
        <option value="2" required>South Euclid</option>
        <option value="3" required>Kirtland</option>
        <option value="4" required>Twinsburg</option>
        <option value="5" required>Bay Village</option>
        <option value="6" required>Huron</option>
        <option value="7" required>Ravenna</option>
        <option value="8" required>Brecksville</option>
        <option value="9" required>Shaker Heights</option>
        <option value="10" required>Wellington</option>
        <option value="11" required>North Olmstead</option>
      </select>

      <label for="imageUrl" class="sr-only">User image URL</label>
      <input
        type="imageUrl"
        id="imageUrl"
        class="form-control"
        placeholder="User image URL"
        v-model="user.userImgUrl"
      />

      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account <i class="fa fa-arrow-right"></i>
      </button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
        emailAddress: "",
        userLocation: "",
        userImgUrl: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
#register {
  margin: auto;
  padding: 2%;
  font-family: Roboto, sans-serif;
  text-align: center;
}

.form-register {
  display: flex;
  margin: 15px 0;
  font-size: 1em;
  text-align: center;
  justify-content: center;
}

label.sr-only {
  font-size: 1.3em;
}

#username,
#password,
#confirmPassword,
#emailAddress,
#location,
#imageUrl {
  max-width: 325px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid black;
  line-height: 25px;
  display: block;
  margin: 15px 0;
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


@media (min-width: 670px) {
  #register {
    text-align: center;
  }

  #username,
  #password,
  #confirmPassword,
  #emailAddress,
  #location {
    margin: 15px 0;
  }

  

  button[type="submit"] {
    max-width: 540px;
  }
}
</style>
