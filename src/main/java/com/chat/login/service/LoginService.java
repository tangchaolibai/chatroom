package com.chat.login.service;

import javax.servlet.http.HttpSession;

public interface LoginService {
	String login(String username, String password, HttpSession session); 
}
