package com.example.exp3.service;

import com.example.exp3.daos.impl.UserDAOImpl;

public class UsersService {
    private final UserDAOImpl userDAO = new UserDAOImpl();

    public boolean validateUser(String username, String password) {
        return userDAO.login(username, password) > 0;
    }
}
