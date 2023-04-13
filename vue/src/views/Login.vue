<template>
  <div id="login" class="text-center">
     <link href='https://fonts.googleapis.com/css?family=Luckiest Guy' rel='stylesheet'>
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="username"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
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
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in <i class="fa fa-arrow-right"></i></button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/profile");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
div#login {
  margin: auto;
  padding: 2%;
  max-width: 100%;
  font-family: Roboto, sans-serif;
  text-align: center;
}

form.form-signin {
  display: flex;
  flex-direction: column;
  max-width: 750px;
  margin: auto;
  text-align: left;
}

form > div.form-input {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  padding-bottom: 8px;
  flex-direction: column;
}

form > div > label,
form > div > input {
  display: inline;
}

form > div > label {
  margin-right: 10px;
}

form > div > input {
  width: 70%;
}

form > button {
  margin-bottom: 15px;
}

form > #register-link {
  max-width: 350px;
  display: block;
}

#username,
#password {
  max-width: 325px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid black;
  line-height: 25px;
  margin: 10px 0px;
  width: 100%;
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

label.sr-only {
  font-size: 1.3em;
}

@media (max-width: 670px) {
  button[type="submit"] {
    width: 65%;
    margin-left: 20px;
  }

  label.sr-only {
    padding: 8px 0;
  }

  form > div.form-input {
    margin-left: 20px;
  }
}

@media (max-width: 400px) {
  form > #register-link {
    margin-left: 9em;
  }
}
</style>