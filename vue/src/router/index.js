import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import RegisterPet from '../views/RegisterPet.vue'
import store from '../store/index'
import Profile from '../views/Profile.vue'
import Contact from '../views/Contact.vue'
import SearchPets from '../components/SearchPets'
import MyPet from '../components/MyPet'
import FindPets from '../views/FindPets'
import ListPlayDates from '../views/ListOfPlayDates'
import DateForm from '../views/DateForm'
import MyProfile from '../views/MyProfile'
import MyDates from '../views/MyDates'



Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    

    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register-pet",
      name: "register-pet",
      component: RegisterPet,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/search-pets",
      name: "search-pets",
      component: SearchPets,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/profile-page",
      name: "profile-page",
      component: MyProfile,
      meta: {
        requiresAuth: false
      }
    },

      
    {
      path: "/MyProfile",
      name: "MyProfile",
      component: MyProfile,
      meta: {
        requiresAuth: false
      }
    },
    

    {
      path: "/contact",
      name: "contact",
      component: Contact,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/pet/:pet_id",
      name: "pet",
      component: MyPet,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/MyDates",
      name: "MyDates",
      component: MyDates,
      meta: {
        requiresAuth: true
      }
    },
  
    {
      path: "/DateForm",
      name: "DateForm",
      component: DateForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/FindPets",
      name: "FindPets",
      component: FindPets,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/ListPlayDates",
      name: "list-play-dates",
      component: ListPlayDates,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    alert ('You must be logged in to view this.');
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
