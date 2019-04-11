package com.stackroute.newsapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.exception.NewsAlreadyExistsException;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.services.NewsService;

import io.jsonwebtoken.Jwts;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/newsservice")
public class NewsController {

	@Autowired
	private NewsService newsSer;

	@PostMapping("/news")
	public ResponseEntity<?> saveNews(@RequestBody News news, HttpServletRequest request, HttpServletRequest response) {
		String userId = getUserIdByRequest(request);
		try {
			news.setUserId(userId);
			newsSer.saveNews(news);
		} catch (NewsAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<News>(news, HttpStatus.CREATED);
	}
	
	@PostMapping("/isNewsAvailable")
	public ResponseEntity<?> isNewsAvailable(@RequestBody News news, HttpServletRequest request, HttpServletRequest response) {
		String userId = getUserIdByRequest(request);
		Boolean isNewsAvailable;
		try {
//			news.setUserId(userId);
//			newsSer.saveNews(news);
			isNewsAvailable =newsSer.isNewsAvailable(news, userId);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Boolean>(isNewsAvailable, HttpStatus.OK);
	}

	String getUserIdByRequest(HttpServletRequest request) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return userId;
	}

	@PutMapping("/news/{title}")
	public ResponseEntity<?> updateNews(@PathVariable("title") String title, HttpServletRequest request,
			@RequestBody News news) {
		String userId = getUserIdByRequest(request);
		try {
		int id = newsSer.getId(userId, title);
		
			newsSer.updateNews(id, news);
		} catch (NewsNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<News>(news, HttpStatus.OK);
	}

	@DeleteMapping("/news/{title}")
	public ResponseEntity<?> deleteNews(@PathVariable("title") String title, HttpServletRequest request) {
		String userId = getUserIdByRequest(request);
		try {
		int id=newsSer.getId(userId, title);
			newsSer.deleteNewsById(id);
		} catch (NewsNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("news deleted sucessfully", HttpStatus.OK);
	}
	
	@GetMapping("/allnews")
	public @ResponseBody ResponseEntity<?> fetchMyAllNews(final ServletRequest req, final ServletResponse res)
			throws NewsNotFoundException {
		final HttpServletRequest request = (HttpServletRequest) req;
		String userId = getUserIdByRequest(request);
		return new ResponseEntity<List<News>>(newsSer.getMyNews(userId), HttpStatus.OK);
	}
	
	@GetMapping("/admin")
	public @ResponseBody ResponseEntity<?> fetchAdminNews(final ServletRequest req, final ServletResponse res)
			throws NewsNotFoundException {
		String userType = "admin";
		return new ResponseEntity<List<News>>(newsSer.getAdminNews(userType), HttpStatus.OK);
	}

}
