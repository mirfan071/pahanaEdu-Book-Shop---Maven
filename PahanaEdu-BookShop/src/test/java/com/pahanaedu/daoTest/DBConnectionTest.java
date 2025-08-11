package com.pahanaedu.daoTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.pahanaedu.dao.DBConnection;

public class DBConnectionTest {

    @Test
    public void testGetInstanceNotNull() {
        // Ensure that the singleton instance is created and not null.
        DBConnection instance = DBConnection.getInstance();
        assertNotNull("The DBConnection instance should not be null", instance);
    }

    @Test
    public void testGetConnectionNotNull() {
        // Check that a valid connection is returned.
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        assertNotNull("The Connection should not be null", connection);
    }

    @Test
    public void testSingletonBehavior() {
        // Ensure that multiple calls to getInstance() return the same instance.
        DBConnection instance1 = DBConnection.getInstance();
        DBConnection instance2 = DBConnection.getInstance();
        assertSame("Both instances should be identical", instance1, instance2);
    }

    @Test
    public void testConnectionValidity() throws SQLException {
        // Use the connection's isValid() method to verify the connection.
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        assertTrue("The connection should be valid", connection.isValid(2));
    }

    @Test
    public void testExecuteSimpleQuery() throws SQLException {
        // Execute a simple query to verify that the connection can interact with the database.
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        try (Statement stmt = connection.createStatement()) {
            boolean result = stmt.execute("SELECT 1");
            assertNotNull("The query execution should complete without returning a null result", result);
        }
    }
}
