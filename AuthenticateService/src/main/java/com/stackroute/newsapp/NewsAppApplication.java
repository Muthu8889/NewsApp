package com.stackroute.newsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
		System.out.println("Welcome to News Application Authentication service");
	}
}
