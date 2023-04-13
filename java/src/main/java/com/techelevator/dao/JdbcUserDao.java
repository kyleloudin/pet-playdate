package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id FROM users WHERE username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

    @Override
    public int findLocationIdByName(String location) {
        if (location == null) throw new IllegalArgumentException("Location cannot be null");

        int locationId;
        try {
            locationId = jdbcTemplate.queryForObject("SELECT location_id FROM location WHERE city = ?", int.class, location);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("Location " + location + " was not found.");
        }

        return locationId;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }



    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }



    @Override
    public boolean create(String username, String password, String role, String emailAddress, LocalDate registeredDate, int location, String imgUrl) {
        String insertUserSql = "INSERT INTO users (username, password_hash, role, email_address, registered_date, location_id, user_img) VALUES (?,?,?,?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();
        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole, emailAddress, registeredDate, location, imgUrl) == 1;
    }

    @Override
    public boolean updateUser(User user){
        String updateUserSql = "UPDATE users SET(username = ?, password_hash = ?, role = ?, email_address = ?, registered_date = ?, location_id = ?, user_img = ?) WHERE user_id = ?";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        return jdbcTemplate.update(updateUserSql, user.getUsername(), password_hash, user.getAuthorities(), user.getEmailAddress(), user.getRegisteredDate(), user.getUserLocation(), user.getUserImgUrl(), user.getId()) == 1;
    }

    @Override
    public boolean deleteUser(int userId) {
            String sql = "DELETE FROM users WHERE user_id = ?;";
            jdbcTemplate.update(sql, userId);
            return true;
        }



    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setEmailAddress(rs.getString("email_address"));
        user.setRegisteredDate(rs.getDate("registered_date").toLocalDate());
        user.setUserLocation(rs.getInt("location_id"));
        user.setUserImgUrl(rs.getString("user_img"));
        return user;
    }
}
