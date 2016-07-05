package com.shentong.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;

import com.shentong.DAO.*;
import com.shentong.service.UserServiceImp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JUnitSerciveTestExample {
	
	@InjectMocks
    UserServiceImp userService;
	
	@Spy
    List<User> users = new ArrayList<User>();

	@Test
	public void testSimpleInt() {
		// create mock
		UserDAO test = Mockito.mock(UserDAOImp.class);
		// define return value for method getUniqueId()
		when(test.findByUsername("Shentong")).thenReturn(new User("name","password","role"));
		// use mock in test
		assertEquals(new User("name","password","role"), test.findByUsername("Shentong"));

		
	}



}