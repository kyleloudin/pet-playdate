import axios from 'axios';

export default {


  registerPet(pet) {
    return axios.post('/register-pet', pet)
  },

  registerPlayDate(playDate) {
    return axios.post('/playdate/new', playDate)
  },

  getAllPlayDates() {
    return axios.get('/playdates/all')
  },

  getMyPlayDates() {
    return axios.get('/playdates/my-playdates')
  },

  getPets(petName) {
    return axios.get(`/pets/view-pets/${petName}`)
  },

  getAllPets() {
    return axios.get('/pets/all')
  },

  getMyPets() {
    return axios.get('/pets/my-pets')
  },

  getAllPetsBarMine() {
    return axios.get('/pets/not-mine')
  },

  getMe() {
    return axios.get('/user/me')
  },

  getAllUsers() {
    return axios.get('/users/all')
  },

  getLocations() {
    return axios.get('/locations/all')
  },

  deletePet(petID) {
    return axios.delete(`/pets/delete/${petID}`)
  },

  deletePlayDate(playDateId) {
    return axios.delete(`/playdates/delete/${playDateId}`)
  }



  // getPet(petID) {
  //   return HTMLOutputElement.get(`/pets/${petID}`).then((response) => {
  //     const pets  = response.data.pets;
  //     return pets.find(pet => pet.pet_id == petID);
  //   })
  // }


}
