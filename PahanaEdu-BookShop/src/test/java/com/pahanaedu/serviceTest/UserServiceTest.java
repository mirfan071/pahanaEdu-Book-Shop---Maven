package com.pahanaedu.serviceTest;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
	

    private UserService userService;
    private User testUser;

    @Before
    public void setUp() throws SQLException {
        userService = UserService.getInstance();

        // Create a test user
        testUser = new User();
        testUser.setFullname("Test User");
        testUser.setUsername("testuser123");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password123");
        testUser.setRole("user");

        // Add user to DB
        userService.addUser(testUser);
    }

    @After
    public void tearDown() throws SQLException {
        // Find and delete test user from DB
        User user = userService.getUserByUsername("testuser123");
        if (user != null) {
            userService.deleteUser(user.getId());
        }
    }

    @Test
    public void testAddAndGetUserByUsername() throws SQLException {
        User fetchedUser = userService.getUserByUsername("testuser123");
        assertNotNull(fetchedUser);
        assertEquals("Test User", fetchedUser.getFullname());
        assertEquals("testuser@example.com", fetchedUser.getEmail());
    }

    @Test
    public void testIsUsernameOrEmailExists() throws SQLException {
        boolean exists = userService.isUsernameOrEmailExists("testuser123", "testuser@example.com");
        assertTrue(exists);

        boolean notExists = userService.isUsernameOrEmailExists("nonexistent", "noemail@example.com");
        assertFalse(notExists);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User user = userService.getUserByUsername("testuser123");
        assertNotNull(user);
        user.setFullname("Updated Name");

        userService.updateUser(user);

        User updated = userService.getUserById(user.getId());
        assertEquals("Updated Name", updated.getFullname());
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        // Add a new user just for delete test
        User tempUser = new User();
        tempUser.setFullname("Delete Me");
        tempUser.setUsername("deleteme123");
        tempUser.setEmail("deleteme@example.com");
        tempUser.setPassword("pass");
        tempUser.setRole("user");

        userService.addUser(tempUser);
        User added = userService.getUserByUsername("deleteme123");
        assertNotNull(added);

        userService.deleteUser(added.getId());
        User deleted = userService.getUserByUsername("deleteme123");
        assertNull(deleted);
    }

}
