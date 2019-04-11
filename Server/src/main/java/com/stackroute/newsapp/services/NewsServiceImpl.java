package com.stackroute.newsapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository repo;

	public NewsServiceImpl(NewsRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException {
		System.out.println("News  ====>>>>> "+news);
		System.out.println("Id  ====>>>>> "+news.getId());
//		if (news.getId() > 0) {
//			throw new NewsAlreadyExistsException("Could not save the News, News Already exsits");
//		}
		repo.save(news);
		return true;
	}

	@Override
	public News updateNews(int id, News updateNews) throws NewsNotFoundException {
		final News news = repo.findById(id).orElse(null);
		if (news == null) {
			throw new NewsNotFoundException("Couldn't update News, News not found!");
		}
		repo.save(news);
		return null;
	}

	@Override
	public boolean deleteNewsById(int id) throws NewsNotFoundException {
		final News news = repo.findById(id).orElse(null);
		if (news == null) {
			throw new NewsNotFoundException("Couldn't delete News, News not found!");
		}
		repo.delete(news);
		return false;
	}

	@Override
	public News getNewsById(int id) throws NewsNotFoundException {
		final News news = repo.findById(id).get();
		if (news == null) {
			throw new NewsNotFoundException("News not found!");
		}
		return news;
	}

	@Override
	public List<News> getMyNews(String userId) throws NewsNotFoundException {
		return repo.findByUserId(userId);
	}

	@Override
	public int getId(String userId, String title) throws NewsNotFoundException {
		List<News> allNews = getMyNews(userId);
		System.out.println("all news---->>"+allNews);
		for(News eachNews: allNews) {
			if(eachNews.getTitle().contains(title)) {
				System.out.println("news Id ------->>>>>"+eachNews.getId());
				return eachNews.getId();
			}
		}
		return 0;
	}

	@Override
	public boolean isNewsAvailable(News news,String userId) throws NewsNotFoundException {
		List<News> allNews = getMyNews(userId);
		for(News eachNews: allNews) {
			if(news.getTitle().contains(eachNews.getTitle())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<News> getAdminNews(String userType) throws NewsNotFoundException {
		return repo.findByUserType(userType);
	}

}
