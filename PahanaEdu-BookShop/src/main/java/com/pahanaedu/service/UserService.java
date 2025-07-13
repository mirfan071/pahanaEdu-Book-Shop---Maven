package com.pahanaedu.service;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;
    private UserDAO userDAO;

    private UserService() {
        userDAO = new UserDAO();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    public boolean isUsernameOrEmailExists(String username, String email) throws SQLException {
        return userDAO.isUsernameOrEmailExists(username, email);
    }

    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    public void deleteUser(int id) throws SQLException {
        userDAO.deleteUser(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public User getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
    }
    
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

}
