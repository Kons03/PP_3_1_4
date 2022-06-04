package com.kata.restapi.DAO;

import com.kata.restapi.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

    User getUserByUsername(String name);
}
