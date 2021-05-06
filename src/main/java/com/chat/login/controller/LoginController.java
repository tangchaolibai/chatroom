package com.chat.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chat.login.service.LoginService;


@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = { "login", "/" }, method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpSession session, @RequestParam String username, @RequestParam String password) {
		return loginService.login(username, password, session);
	}
}
