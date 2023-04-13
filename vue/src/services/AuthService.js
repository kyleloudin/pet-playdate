import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  updateUser(user) {
    return axios.put(`/users/find/${user.userId}`, user);
  },

  contact() {
    return axios.post('/contact')
  },



}
