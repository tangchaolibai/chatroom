package com.chat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.chat.login.dao.UserDao;

@SpringBootApplication
public class ChatApplication extends SpringBootServletInitializer {
	
	@Autowired
	private UserDao userDao;
	
	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}
	
	@PostConstruct
	public void initdate() {
		userDao.loadUserInfo();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ChatApplication.class);
	}
}
