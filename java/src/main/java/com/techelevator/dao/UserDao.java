package com.techelevator.dao;

import com.techelevator.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findLocationIdByName(String location);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, String emailAddress, LocalDate registeredDate, int location, String imgUrl);

    boolean updateUser(User user);

    boolean deleteUser(int id);
}
