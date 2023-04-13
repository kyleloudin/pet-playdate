<template>
  <div>
     

<div class="container">
    <form action="/action_page.php">
    <h2>Account Settings</h2>
    <label for="password">New Password</label>
    <input type="password" id="password" name="password" placeholder="New Password"> 

    <label for="password">Confirm Password</label>
    <input type="password" id="password" name="password" placeholder="Confirm Password">

    <label for="email">Change Email Address</label>
    <input type="emailAddress" id="emailAddress" name="email" placeholder="Enter New Email">

    <label for="imgUrl">Change Profile Picture</label><br />
    <input type="file" id="file" name="imgUrl"  placeholder="User Image URL"><br />

    <input type="submit" value="Save">

    
  </form>
</div>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";

export default {
  name: "profile-page",

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
    };
  },
  methods: {
    update() {
      const newUser = {
        userId: Number(this.$route.params.userId),
        username: this.user.username,
        password: this.user.password,
        confirmPassword: this.user.confirmPassword,
        role: this.user.role,
        emailAddress: this.user.emailAddress,
        userLocation: this.user.userLocation,
        userImgUrl: "",
      };

      if (this.userId === 0) {
        // add
        AuthService.register(newUser)
          .then((response) => {
            if (response.status === 201) {
              this.$router.push(`/users/${newUser.userId}`);
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "adding");
          });
      } else {
        // update
        newUser.userId = this.userId;
        newUser.password = this.user.password;
        newUser.emailAddress = this.user.emailAddress;
        AuthService.updateUser(newUser)
          .then((response) => {
            if (response.status === 200) {
              this.$router.push(`/users/${newUser.userId}`);
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "updating");
          });
      }
    },
  },
};
</script>

<style>

h3 {
  text-align: center;
}

.container {
  display: flex;
  justify-content: rig;
  align-content: center;
  align-self: center;
  text-align: center;
  
}

form {
  display: flex;
  justify-content: center;
  align-content: center;
  align-self: center;
  text-align: center;
}

#username,
#password,
#confirmPassword,
#emailAddress,
#imageUrl {
  justify-content: center;
  align-content: center;
  align-self: center;
  text-align: center;
  }



  




</style>