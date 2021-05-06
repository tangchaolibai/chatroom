package com.chat.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chat.login.entity.User;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

public class Constant {
	
	public static final String USER_TOKEN = "userId";
	
	public static Map<String, User> userMap = new HashMap<String, User>();
	public static Map<String, WebSocketServerHandshaker> webSocketServerHandshakerMap = 
		new ConcurrentHashMap<String, WebSocketServerHandshaker>();
	
	public static Map<String, ChannelHandlerContext> onlineUserMap = 
	        new ConcurrentHashMap<String, ChannelHandlerContext>();
}
