package com.stackroute.newsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newsapp.domain.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
	
	List<News> findByUserId(String userId);
	
	List<News> findByUserIdAndTitle(String userId, String title);
	
	List<News> findByUserType(String userType);

}
