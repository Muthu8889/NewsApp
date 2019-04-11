package com.stackroute.newsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.newsapp.execeptions.UserAlreadyExsistsException;
import com.stackroute.newsapp.execeptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.repositories.UserRepository;
import com.stackroute.newsapp.services.UserServiceImpl;

public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	private User user;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	Optional<User> options;
	
	@Before
	public void setUp() throws Exception {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String username = new String(array, Charset.forName("UTF-8"));
		MockitoAnnotations.initMocks(this);
		user = new User(username, "pass", "firstname", "lastname", "admin", new Date());
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserNotFoundException, UserAlreadyExsistsException{
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("Cannot Register User", true, flag);
		verify(userRepository, times(1)).save(user);
	}
	
	@Test(expected = UserAlreadyExsistsException.class)
	public void testSaveUserFailure() throws UserNotFoundException, UserAlreadyExsistsException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		@SuppressWarnings("unused")
		boolean flag = userServiceImpl.saveUser(user);
	}
	
	@Test
	public void testValidateSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals(user.getUserId(), userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException {
		when(userRepository.findByUserId("muthu")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}
