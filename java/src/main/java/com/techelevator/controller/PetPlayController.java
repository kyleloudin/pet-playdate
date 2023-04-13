package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.LocationDao;
import com.techelevator.dao.PetDao;
import com.techelevator.dao.PlayDateDao;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class PetPlayController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;
    private PetDao petDao;
    private PlayDateDao playDateDao;
    private LocationDao locationDao;

    public PetPlayController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, PetDao petDao, PlayDateDao playDate, LocationDao location) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.petDao = petDao;
        this.playDateDao = playDate;
        this.locationDao = location;
    }

// Made this to view other users' profiles

    @RequestMapping(value = "/users/find/{userId}", method = RequestMethod.GET)
    public User userById(@PathVariable int userId) {
        User user = userDao.getUserById(userId);
        return user;
    }

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public List<User> users() {
        List<User> users = userDao.findAll();
        return users;
    }


    @RequestMapping(value = "/user/me", method = RequestMethod.GET)
    public User whoAmI(Principal principal) {
        User user = userDao.getUserById(userDao.findIdByUsername(principal.getName()));
        return user;
    }

// Made this to (hopefully!) update my own profile

    @RequestMapping(value = "/users/edit", method = RequestMethod.PUT)
    public User editMyAccount(@RequestBody Principal principal, String password, String confirmPassword, String emailAddress, int location, String imgUrl) {

        User meUser = userDao.findByUsername(principal.getName());
        if (!password.equals("")) {
            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not be match");
            }
            meUser.setPassword(password);
        }
        if (!emailAddress.equals("")) {
            meUser.setEmailAddress(emailAddress);
        }
        meUser.setUserLocation(location);
        if (!imgUrl.equals("")) {
            meUser.setUserImgUrl(imgUrl);
        }
        userDao.updateUser(meUser);
        return meUser;
    }

    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public List<Pet> listPets() {
        return petDao.findAll();
    }

    @RequestMapping(path = "/pets/delete/{petId}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable int petId) {
        petDao.deletePet(petId);
    }

    @RequestMapping(path = "/pet/{petId}", method = RequestMethod.GET)
    public Pet getPetByPetId(@PathVariable int petId) {
        Pet pet = petDao.getPetByPetId(petId);
        if (pet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found.");
        } else {
            return pet;
        }
    }

    @RequestMapping(path = "/pets/my-pets", method = RequestMethod.GET)
    public List<Pet> listMyPets(Principal principal) {
        List<Pet> allPets = petDao.findAll();
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : allPets) {
            if(pet.getUserId()== userDao.findIdByUsername(principal.getName())){
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }

    @RequestMapping(path = "/pets/all", method = RequestMethod.GET)
    public List<Pet> findAllPets() {
        return petDao.findAll();
    }

    @RequestMapping(path = "/pets/not-mine", method = RequestMethod.GET)
    public List<Pet> listNotMyPets(Principal principal) {
        List<Pet> allPets = petDao.findAll();
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : allPets) {
            if(pet.getUserId() != userDao.findIdByUsername(principal.getName())){
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }



    @RequestMapping(path = "/pets/view-pets/{petName}", method = RequestMethod.GET)
    public List<Pet> listPetsWithNamesLike(@PathVariable String petName) {
        List<Pet> allPets = petDao.findAll();
        List<Pet> filteredPets = new ArrayList<>();
        for (Pet pet : allPets) {
            if(pet.getPetName().toLowerCase().contains(petName.toLowerCase())){
                filteredPets.add(pet);
            }
        }
        return filteredPets;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register-pet", method = RequestMethod.POST)
    public void registerPet(@Valid @RequestBody RegisterPetDto newPet, Principal principal) {
        if(!petDao.createPet(userDao.findIdByUsername(principal.getName()), newPet.getPetName(), newPet.getRegisteredDate(), newPet.getAnimal(), newPet.getBreed(), newPet.getGender(), newPet.getRating(), newPet.getSize(), newPet.getPersonality(), newPet.getAge(), newPet.isSpayedNeutered(), newPet.getDescription(), newPet.getFavoriteToy(), newPet.getPetImgUrl())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet registration failed.");
        }
    }

    @RequestMapping(path = "/playdates/all", method = RequestMethod.GET)
    public List<PlayDate> listAll() {
        return playDateDao.findAll();
    }

    @RequestMapping(path = "/playdates/delete/{playDateId}", method = RequestMethod.DELETE)
    public void deleteDate(@PathVariable int playDateId) {
        playDateDao.deleteDate(playDateId);
    }

    @RequestMapping(path = "/playdates/my-playdates", method = RequestMethod.GET)
    public List<PlayDate> listMyPlayDates(Principal principal) {
        List<PlayDate> allPlayDates = playDateDao.findAll();
        List<PlayDate> filteredPlayDates = new ArrayList<>();
        for (PlayDate playDate : allPlayDates) {
            if((playDateDao.getUserIdFromPlayDateId(playDate.getPlayDateId()) == userDao.findIdByUsername(principal.getName()))) {
                filteredPlayDates.add(playDate);
            }
        }
        return filteredPlayDates;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/playdate/new", method = RequestMethod.POST)
    public void schedulePlayDate(@Valid @RequestBody RegisterPlayDateDto newPlayDate) {
        if(!playDateDao.createPlayDate(newPlayDate.getPetId(), newPlayDate.getLocationId(), newPlayDate.getScheduledDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date registration failed.");
        }
    }

    @RequestMapping(path = "/locations/all", method = RequestMethod.GET)
    public List<Location> listAllLocations() {
        return locationDao.getPlayLocations();
    }

}
