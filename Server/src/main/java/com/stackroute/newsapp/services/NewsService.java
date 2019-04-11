package com.stackroute.newsapp.services;

import java.util.List;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;

public interface NewsService {
	
	boolean saveNews(News news) throws NewsAlreadyExistsException;
	
	News updateNews(int id,News updateNews) throws NewsNotFoundException;
	
	boolean deleteNewsById(int id) throws NewsNotFoundException;
	
	News getNewsById(int id) throws NewsNotFoundException;
	
	List<News> getMyNews(String useId) throws NewsNotFoundException;
	
	List<News> getAdminNews(String userType) throws NewsNotFoundException;
	
	int getId(String userId, String title) throws NewsNotFoundException;
	
	boolean isNewsAvailable(News news, String userId)  throws NewsNotFoundException;

}
