package com.chat.inint;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chat.login.dao.UserDao;
import com.chat.websocket.WebSocketServer;

@Component
@Scope("singleton")
public class InintContext {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WebSocketServer webSocketServer;
	
	private Thread nettyThread;

	@PostConstruct
	public void init() {
		nettyThread = new Thread(webSocketServer);
		nettyThread.start();
		userDao.loadUserInfo();
	}
	
	 @SuppressWarnings("deprecation")
	    @PreDestroy
	    public void close() {
	        webSocketServer.close();
	        nettyThread.stop();
	    }
}
