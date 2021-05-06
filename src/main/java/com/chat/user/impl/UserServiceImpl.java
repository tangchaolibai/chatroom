package com.chat.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.ResponseData;
import com.chat.login.dao.UserDao;
import com.chat.login.entity.User;
import com.chat.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseData getUserById(String userId) {
		User  user = userDao.getUserById(userId);
		ResponseData responseData = new ResponseData();
		responseData.success();
		return new ResponseData().success().setData("user", user);
	}

}
