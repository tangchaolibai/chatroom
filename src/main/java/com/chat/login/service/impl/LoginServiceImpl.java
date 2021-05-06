package com.chat.login.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.constant.Constant;
import com.chat.login.dao.UserDao;
import com.chat.login.entity.User;
import com.chat.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public String login(String username, String password, HttpSession session) {
		User user = userDao.getByUsername(username);
		if (user == null) {
			return "不存在该用户名";
		}
		session.setAttribute(Constant.USER_TOKEN, user.getUserId());
		return "200";
	}

}
