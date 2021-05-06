package com.chat.login.dao;

import com.chat.login.entity.User;

public interface UserDao {

	void loadUserInfo();

	User getByUsername(String username);
	
	User getUserById(String userId);

}
