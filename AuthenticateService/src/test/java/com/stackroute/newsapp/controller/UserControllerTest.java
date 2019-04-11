package com.stackroute.newsapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.newsapp.execeptions.UserAlreadyExsistsException;
import com.stackroute.newsapp.execeptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.services.SecurityTokenGenerator;
import com.stackroute.newsapp.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = { UserController.class })
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;
	private User user;

	@InjectMocks
	UserController userController;

	@Before
	public void setUp() throws Exception {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String username = new String(array, Charset.forName("UTF-8"));
		MockitoAnnotations.initMocks(this);
		user = new User(username, "pass", "firstname", "lastname", "admin", new Date());
	}
	
	@Test
	public void testRegisterUser() throws Exception, UserAlreadyExsistsException {
		when(userService.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/api/userservice/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated())
				.andDo(print());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}
	
	@Test
	public void testLoginUser() throws Exception, UserNotFoundException, UserAlreadyExsistsException {
		String username = user.getUserId();
		String password = user.getPassword();
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(username, password)).thenReturn(user);
		mockMvc.perform(post("/api/userservice/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
	}
	
	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
