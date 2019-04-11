package com.stackroute.newsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.repository.NewsRepository;
import com.stackroute.newsapp.services.NewsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {
	
	@InjectMocks
	private NewsServiceImpl newsServiceImpl;
	
	@Mock
	private NewsRepository newsRepository;
	
	@Mock
	private News news;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		news = new News(109876, "title", "description", "url", "urlToImage", "username","admin");
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(news);
		assertNotNull("jpaRepository creation fails: use @injectMocks on newsServicempl", newsRepository);
	}
	
	@Test
	public void testSaveNewsTest() throws Exception {
		when(newsRepository.save(news)).thenReturn(news);
		boolean flag = newsServiceImpl.saveNews(news);
		assertEquals("saving movie failed,the call to movieDAOImpl is returning false,check this method", true,
				flag);
		verify(newsRepository, times(1)).save(news);
	}
	
	@Test
	public void deleteTest() throws Exception{
		News news = new News(109876, "title", "description", "url", "urlToImage", "username","admin");
		Optional<News> newsOption = Optional.of(news);
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(newsOption);
		boolean rtn = newsServiceImpl.deleteNewsById(109876);
		assertEquals(false, rtn);
		verify(newsRepository,times(1)).delete(news);
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteTest1() throws Exception{
		News news = null;
		when(newsRepository.findById(Mockito.anyInt())).thenReturn(null);
		boolean rtn = newsServiceImpl.deleteNewsById(109876);
		assertEquals(true, rtn);
		verify(newsRepository,times(1)).delete(news);
	}
	
	@Test
	public void getNewsTest() throws Exception {
		List<News> list = new ArrayList<>();
		News news = new News(109876, "title", "description", "url", "urlToImage", "username","admin");
		list.add(news);
		when(newsRepository.findByUserId("username")).thenReturn(list);
		List<News> rtn = newsServiceImpl.getMyNews("username");
		assertEquals("title", rtn.get(0).getTitle());
		verify(newsRepository, times(1)).findByUserId(Mockito.anyString());
	}
	
}
