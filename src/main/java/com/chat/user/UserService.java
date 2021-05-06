package com.chat.user;

import com.chat.entity.ResponseData;
import com.chat.login.entity.User;

public interface UserService {
	ResponseData getUserById(String userId);
}
