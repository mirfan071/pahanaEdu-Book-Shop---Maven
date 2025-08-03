package com.pahanaedu.modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.model.User;

public class UserModelTest {
	
	  private User user;

	    @Before
	    public void setUp() {
	        user = new User(1, "Kamal Perera", "kamal", "kamal@gmail.com", "123456", "manager");
	    }

	    @Test
	    public void testUserConstructorWithAllFields() {
	        assertEquals(1, user.getId());
	        assertEquals("Kamal Perera", user.getFullname());
	        assertEquals("kamal", user.getUsername());
	        assertEquals("kamal@gmail.com", user.getEmail());
	        assertEquals("123456", user.getPassword());
	        assertEquals("manager", user.getRole());
	    }

	    @Test
	    public void testSettersAndGetters() {
	        user.setId(10);
	        user.setFullname("Saman Kumara");
	        user.setUsername("saman");
	        user.setEmail("saman@gmail.com");
	        user.setPassword("123456");
	        user.setRole("cashier");

	        assertEquals(10, user.getId());
	        assertEquals("Saman Kumara", user.getFullname());
	        assertEquals("saman", user.getUsername());
	        assertEquals("saman@gmail.com", user.getEmail());
	        assertEquals("123456", user.getPassword());
	        assertEquals("cashier", user.getRole());
	    }

	    @Test
	    public void testEmptyConstructor() {
	        User emptyUser = new User();
	        assertEquals(0, emptyUser.getId());
	        assertNull(emptyUser.getFullname());
	        assertNull(emptyUser.getUsername());
	        assertNull(emptyUser.getEmail());
	        assertNull(emptyUser.getPassword());
	        assertNull(emptyUser.getRole());
	    }

}
