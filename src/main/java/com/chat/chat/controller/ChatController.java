package com.chat.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chat.constant.Constant;
import com.chat.entity.ResponseData;
import com.chat.user.UserService;

@Controller
@RequestMapping("/chatroom")
public class ChatController {

	@Autowired
	private UserService userService;
	
	  @RequestMapping(method = RequestMethod.GET)
	public String toChatroom() {
        return "chatroom";
    }
	
	@RequestMapping(path = "/get_userinfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData getUser(HttpSession session) {
		Object userId = session.getAttribute(Constant.USER_TOKEN);
		return userService.getUserById((String)userId);
	}
}
