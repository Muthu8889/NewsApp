package com.stackroute.newsapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.services.NewsService;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {

	private MockMvc newsMockMVC;

	@Mock
	private NewsService newsService;

	@InjectMocks
	NewsController newsController;
	
	private List<News> newsSet = new ArrayList<>();
	private News news = new News(109876, "title", "description", "url", "urlToImage", "username","admin");
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		newsMockMVC = MockMvcBuilders.standaloneSetup(newsController).build();
		 news = new News(10986, "title", "description", "url", "urlToImage", "username","admin");
		News news1 = new News(10976, "title", "description", "url", "urlToImage", "username","admin");
		News news2 = new News(10876, "title", "description", "url", "urlToImage", "username","admin");
		News news3 = new News(19876, "title", "description", "url", "urlToImage", "username","admin");
		News news4 = new News(10987, "title", "description", "url", "urlToImage", "username","admin");
		News news5 = new News(109876, "title", "description", "url", "urlToImage", "username","admin");
		
		newsSet.add(news1);
		newsSet.add(news2);
		newsSet.add(news3);
		newsSet.add(news4);
		newsSet.add(news5);
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void  saveNewsTest() throws Exception{
		when(newsService.saveNews(news)).thenReturn(true);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(post("/api/newsservice/news").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isCreated()).andDo(print());
		verify(newsService, times(1)).saveNews(Mockito.any(News.class));
		verifyNoMoreInteractions(newsService);
	}
	
	@Test
	public void deleteNewsTest() throws Exception{
		when(newsService.deleteNewsById(Mockito.anyInt())).thenReturn(true);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(delete("/api/newsservice/news/title").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).deleteNewsById((Mockito.anyInt()));
	}
	
	@Test
	public void fetchNewsTest() throws Exception{
		when(newsService.getMyNews(Mockito.anyString())).thenReturn(newsSet);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(get("/api/newsservice/allnews").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).getMyNews(Mockito.anyString());
	}
	
	@Test
	public void fetchNewsTestForExternal() throws Exception{
		when(newsService.getAdminNews(Mockito.anyString())).thenReturn(newsSet);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMyIsImlhdCI6MTU0ODk0NDMyNX0.DfoA735TswtFG-IXrvuajm18JIfFg4iXINsqa3XvDrI";
		newsMockMVC.perform(get("/api/newsservice/admin").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news)))
		.andExpect(status().isOk()).andDo(print());
		verify(newsService, times(1)).getAdminNews(Mockito.anyString());
	}

}
